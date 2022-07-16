import React, { useContext, useEffect, useState } from "react";
import {
  Container,
  InputLabel,
  MenuItem,
  Select,
  TextField,
  Button,
  Grid,
  FormHelperText,
} from "@mui/material";
import { busca, buscaDirecionada } from "../../api/api.js";
import inputNormalValue from "../../models/inputNormalValue.js";
import ValidacaoContext from "../../context/validacaoContext.js";
import useValidation from "../../validation";
import InputMask from "react-input-mask";

function Endereco({ onSent, dados }) {
  const [pais, setPais] = useState(selectNormalValue(dados.pais));
  const [paises, setPaises] = useState([]);
  const [estado, setEstado] = useState(selectNormalValue(dados.estado));
  const [estados, setEstados] = useState([]);
  const [cidade, setCidade] = useState(selectNormalValue(dados.cidade));
  const [cidades, setCidades] = useState([]);
  const [cep, setCep] = useState(inputNormalValue(dados.cep));
  const [rua, setRua] = useState(inputNormalValue(dados.rua));
  const [fachada, setFachada] = useState(inputNormalValue(dados.fachada));
  const [bairro, setBairro] = useState(inputNormalValue(dados.bairro));
  const [complemento, setComplemento] = useState(
    inputNormalValue(dados.complemento)
  );

  const validations = useContext(ValidacaoContext);
  const [erros, validation, valid] = useValidation(validations);

  function selectNormalValue(dados, dadosUpdate) {
    if (dadosUpdate !== undefined) {
      return dadosUpdate;
    }
    if (dados !== undefined) {
      return dados;
    }
    return 0;
  }

  function noOption() {
    if (pais !== 1058) {
      return <MenuItem value="1">Nenhuma opção</MenuItem>;
    }
    return;
  }

  function validateOnClick() {
    validation(cep, "cep");
    validation(pais, "pais");
    validation(estado, "estado");
    validation(cidade, "cidade");
  }

  useEffect(() => {
    busca("/paises", setPaises);
    if (pais !== 0) {
      buscaDirecionada(
        "http://localhost:8080/estadosDirecionados",
        pais,
        setEstados
      );
    }
    if (estado !== 0) {
      buscaDirecionada(
        "http://localhost:8080/cidadesDirecionadas",
        estado,
        setCidades
      );
    }
  }, [pais, estado]);

  return (
    <form
      onSubmit={(event) => {
        event.preventDefault();
        if (valid()) {
          onSent({
            pais,
            estado,
            cidade,
            cep,
            rua,
            fachada,
            bairro,
            complemento,
          });
        }
      }}
    >
      <Container>
        <Grid container spacing={2} pt={2} pb={2}>
          <Grid item xs={4}>
            <InputLabel>Pais</InputLabel>
            <Select
              value={pais}
              name="pais"
              id="pais"
              required
              fullWidth
              onChange={(event) => {
                setPais(event.target.value);
                setEstado(0);
                setCidade(0);
              }}
              error={!erros.pais.valido}
              onBlur={() => validation(pais, "pais")}
            >
              <MenuItem value={0}>Selecione um Pais</MenuItem>
              {paises.map((pais, index) => (
                <MenuItem key={index} value={pais.codigo}>
                  {pais.nome}
                </MenuItem>
              ))}
            </Select>
            {!erros.pais.valido && (
              <FormHelperText error={!erros.pais.valido}>
                {erros.pais.texto}
              </FormHelperText>
            )}
          </Grid>

          <Grid item xs={4} pt={2}>
            <InputLabel>Estado</InputLabel>
            <Select
              id="estado"
              name="estado"
              value={estado}
              fullWidth
              onChange={(event) => {
                setEstado(event.target.value);
                setCidade(0);
              }}
              error={!erros.estado.valido}
              onBlur={() => validation(estado, "estado")}
            >
              <MenuItem value={0}>Selecione um Estado</MenuItem>
              {noOption()}
              {estados.map((estado, index) => (
                <MenuItem key={index} value={estado.sigla}>
                  {estado.nome}
                </MenuItem>
              ))}
            </Select>
            {!erros.estado.valido && (
              <FormHelperText error={!erros.estado.valido}>
                {erros.estado.texto}
              </FormHelperText>
            )}
          </Grid>

          <Grid item xs={4} pt={2}>
            <InputLabel>Cidade</InputLabel>
            <Select
              id="cidade"
              name="cidade"
              value={cidade}
              fullWidth
              onChange={(event) => setCidade(event.target.value)}
              error={!erros.cidade.valido}
              onBlur={() => validation(cidade, "cidade")}
            >
              <MenuItem value={0}>Selecione uma Cidade</MenuItem>
              {noOption()}
              {cidades.map((cidade, index) => (
                <MenuItem key={index} value={cidade.codigo}>
                  {cidade.nome}
                </MenuItem>
              ))}
            </Select>
            {!erros.cidade.valido && (
              <FormHelperText error={!erros.cidade.valido}>
                {erros.cidade.texto}
              </FormHelperText>
            )}
          </Grid>
        </Grid>

        <Grid container spacing={2} pb={2}>
          <Grid item xs={3}>
            <InputLabel>CEP</InputLabel>
            <InputMask
              mask="99999-999"
              value={cep}
              disabled={false}
              required
              maskChar=""
              onChange={(event) =>
                setCep(event.target.value.replace(/\D/g, ""))
              }
              onBlur={() => validation(cep, "cep")}
            >
              {() => (
                <TextField
                  id="cep"
                  name="cep"
                  variant="outlined"
                  required
                  fullWidth
                  type="text"
                  placeholder="11111-111"
                  error={!erros.cep.valido}
                  helperText={erros.cep.texto}
                />
              )}
            </InputMask>
          </Grid>
          <Grid item xs={9}>
            <InputLabel>Rua</InputLabel>
            <TextField
              id="rua"
              variant="outlined"
              fullWidth
              value={rua}
              type="text"
              required
              placeholder="Avenida fulano da silva"
              onChange={(event) => setRua(event.target.value)}
            />
          </Grid>
        </Grid>

        <Grid container spacing={2} pb={2}>
          <Grid item xs={6}>
            <InputLabel>Fachada</InputLabel>
            <TextField
              id="fachada"
              variant="outlined"
              fullWidth
              value={fachada}
              type="text"
              required
              placeholder="Digite a fachada de sua casa"
              onChange={(event) => setFachada(event.target.value)}
            />
          </Grid>

          <Grid item xs={6}>
            <InputLabel>Bairro</InputLabel>
            <TextField
              id="bairro"
              variant="outlined"
              fullWidth
              value={bairro}
              type="text"
              required
              placeholder="Ciclano da mendonça"
              onChange={(event) => setBairro(event.target.value)}
            />
          </Grid>
        </Grid>

        <Grid container pb={2}>
          <InputLabel>Complemento</InputLabel>
          <TextField
            id="complemento"
            variant="outlined"
            fullWidth
            value={complemento}
            type="text"
            placeholder="Digite um complemento para ajudar a encontrar sua casa"
            onChange={(event) => setComplemento(event.target.value)}
          />
        </Grid>

        <Button
          onClick={() => {
            validateOnClick();
          }}
          margin="normal"
          variant="contained"
          type="submit"
        >
          Proximo
        </Button>
      </Container>
    </form>
  );
}

export default Endereco;
