import React, { useState } from "react";
import Pessoal from "../Pessoal";
import Cabecalho from "../cabecalho";
import Endereco from "../Endereco";
import Stepper from "react-stepper-horizontal";
import TipoPessoa from "../TipoPessoa";
import Contato from "../contato";


function FormCadastro({
  coletarDados,
  coletarContatos,
  deletarContato,
  updateContato,
  dadosContato,
  dadosPessoa,
  setSendDados,
  changePage
}) {
  const [etapas, setEtapas] = useState(0);
  const [contatoId, setContatoId] = useState(0);

  const steps = [
    {
      title: "Pessoa",
      onClick: (event) => {
        event.preventDefault();
        setEtapas(0);
      },
    },
    {
      title: "TipoPessoa",
      onClick: (event) => {
        event.preventDefault();
        setEtapas(1);
      },
    },
    {
      title: "Endereco",
      onClick: (event) => {
        event.preventDefault();
        setEtapas(2);
      },
    },
    {
      title: "Contato",
      onClick: (event) => {
        event.preventDefault();
        setEtapas(3);
      },
    },
  ];

  function proximo(dados) {
    coletarDados(dados)
    setEtapas(etapas + 1);
  }

  function getContatoId() {
    setContatoId(contatoId + 1);
    return contatoId;
  }

  const formularios = [
    <Pessoal onSent={proximo} dados={dadosPessoa} />,
    <TipoPessoa onSent={proximo} dados={dadosPessoa} />,
    <Endereco onSent={proximo} dados={dadosPessoa} />,
    <Contato
      onSent={coletarContatos}
      dados={dadosContato}
      updateContato={updateContato}
      deletarContato={deletarContato}
      getContatoId={getContatoId}
      sendDados={setSendDados}
      changePage={changePage}
    />,
  ];

  return (
    <main>
      <Cabecalho titulo={"CADASTRO"} />
      <Stepper
        defaultColor="#006400"
        activeColor="#7CFC00"
        completeColor="green"
        circleFontColor="black"
        steps={steps}
        activeStep={etapas}
      />
      {formularios[etapas]}
    </main>
  );
}

export default FormCadastro;
