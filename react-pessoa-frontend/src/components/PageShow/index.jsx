import React, { useState, useEffect } from "react";
import Home from "../Home";
import ValidacaoContext from "../../context/validacaoContext";
import {
  validateCnae,
  validateRG,
  validateSize,
  validateTelefone,
  validateSelect,
  validateCep,
  validateEmail,
} from "../../models/cadastro";
import FormCadastro from "../formCadastro";
import { send, sendUpdatePessoa } from "../../api/api";

function PageShow() {
  const [show, setShow] = useState(0);
  const [dadosPessoa, setDadosPessoa] = useState([]);
  const [sendToPut, setSendToPut] = React.useState(false);
  const [dadosContato, setDadosContato] = useState([]);
  const [dadosDto] = useState({ pessoaDto: [], contatoDto: [] });
  const [sendDados, setSendDados] = useState(0);

  function changePage() {
    if (show === 0) {
      setShow(1);
    } else {
      setShow(0);
    }
  }

  function novaPessoa() {
    setDadosPessoa([]);
    setDadosContato([]);
    changePage();
  }

  function updatePessoa(pessoa) {
    if(pessoa.estado != null) {
      setDadosPessoa({
        id: pessoa.pessoa.idPessoa,
        nome: pessoa.pessoa.nome,
        email: pessoa.pessoa.email,
        pais: pessoa.pais.codigo,
        estado: pessoa.estado.sigla,
        cidade: pessoa.cidade.codigo,
        fachada: pessoa.pessoaEndereco.fachada,
        cep: pessoa.pessoaEndereco.cep,
        rua: pessoa.pessoaEndereco.rua,
        bairro: pessoa.pessoaEndereco.bairro,
        complemento: pessoa.pessoa.complemento,
        tipo: pessoa.pessoa.tipoPessoa,
        rg: pessoa.pessoaFisica.rg,
        numero: pessoa.pessoaTelefone.ddd + pessoa.pessoaTelefone.telefone,
      });
    } else {
      setDadosPessoa({
        id: pessoa.pessoa.idPessoa,
        nome: pessoa.pessoa.nome,
        email: pessoa.pessoa.email,
        pais: pessoa.pais.codigo,
        estado: 1,
        cidade: 1,
        fachada: pessoa.pessoaEndereco.fachada,
        cep: pessoa.pessoaEndereco.cep,
        rua: pessoa.pessoaEndereco.rua,
        bairro: pessoa.pessoaEndereco.bairro,
        complemento: pessoa.pessoa.complemento,
        tipo: pessoa.pessoa.tipoPessoa,
        rg: pessoa.pessoaFisica.rg,
        numero: pessoa.pessoaTelefone.ddd + pessoa.pessoaTelefone.telefone,
      });
    }
    pessoa.contatoList.map((contato, index) => {
        contato.id = index;
        return null;
    });
    dadosContato.push(...pessoa.contatoList);
    setSendToPut(true);
    changePage();
  }

  useEffect(() => {
    if (sendToPut && sendDados === 1) {
      dadosDto.pessoaDto = { ...dadosPessoa };
      dadosDto.contatoDto = dadosContato;
      sendUpdatePessoa("http://localhost:8080/updatePessoa", dadosDto);
      setSendDados(0);
      setSendToPut(0);
      setDadosContato([]);
    } else if (sendDados === 1) {
      dadosDto.pessoaDto = { ...dadosPessoa };
      dadosDto.contatoDto = dadosContato;
      send("http://localhost:8080/pessoa", dadosDto);
      setSendDados(0);
      setDadosContato([]);
    }
  }, [dadosDto, sendDados, dadosContato, dadosPessoa, sendToPut]);

  function coletarDados(dados) {
    setDadosPessoa({ ...dadosPessoa, ...dados });
  }

  function coletarContatos(dados) {
    dadosContato.push(dados);
  }

  function deletarContato(index) {
    dadosContato.splice(index, 1);
  }

  function updateContato(contatoUpdate) {
    for (const contato of dadosContato) {
      if (contato.id === contatoUpdate.id) {
        contato.nome = contatoUpdate.nome;
        contato.email = contatoUpdate.email;
        contato.numero = contatoUpdate.numero;
        contato.data = contatoUpdate.data;
        break;
      }
    }
  }

  const screens = [
    <Home updatePessoa={updatePessoa} novaPessoa={novaPessoa} />,
    <ValidacaoContext.Provider
      value={{
        rg: validateRG,
        cnae: validateCnae,
        numero: validateTelefone,
        email: validateEmail,
        nome: validateSize,
        pais: validateSelect,
        estado: validateSelect,
        cidade: validateSelect,
        cep: validateCep,
      }}
    >
      <FormCadastro
        changePage={changePage}
        coletarDados={coletarDados}
        coletarContatos={coletarContatos}
        deletarContato={deletarContato}
        updateContato={updateContato}
        dadosPessoa={dadosPessoa}
        dadosContato={dadosContato}
        setSendDados={setSendDados}
      />
    </ValidacaoContext.Provider>,
  ];

  return <main>{screens[show]}</main>;
}

export default PageShow;
