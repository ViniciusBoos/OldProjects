import React, { useState, useContext, useEffect } from "react";
import { TextField, Button, Container, Grid, InputLabel } from "@mui/material";
import ValidacaoContext from "../../context/validacaoContext";
import useValidation from "../../validation";
import InputMask from "react-input-mask";
import inputNormalValue from "../../models/inputNormalValue";
import { busca } from "../../api/api";

function Pessoal({ onSent, dados }) {
  const [nome, setNome] = useState(inputNormalValue(dados.nome));
  const [email, setEmail] = useState(inputNormalValue(dados.email));
  const [obsNumero, setObsNumero] = useState(inputNormalValue(dados.obsNumero));
  const [numero, setNumero] = useState(inputNormalValue(dados.numero));
  const [emails, setEmails] = useState([]);
  const [getEmails, setGetEmails] = useState(1);
  const [errorEmail, setErrorEmail] = useState({ valido: true, texto: "" });

  const validations = useContext(ValidacaoContext);
  const [erros, validation, valid] = useValidation(validations);

  useEffect(() => {
    if (getEmails === 1) {
      busca("/emails", setEmails);
      setGetEmails(0);
    }
  }, [emails, getEmails]);

  return (
    <form
      onSubmit={(event) => {
        event.preventDefault();
        if (valid() && errorEmail.valido) {
          onSent({ nome, email, numero, obsNumero });
        }
      }}
    >
      <Container>
        <Grid container spacing={2} pt={2}>
          <Grid item xs={6} pb={2}>
            <InputLabel>Nome completo</InputLabel>
            <TextField
              id="nome"
              name="nome"
              placeholder="Fulano da siclano"
              variant="outlined"
              required
              fullWidth
              value={nome}
              onChange={(event) => setNome(event.target.value)}
              onBlur={() => validation(nome, "nome")}
              error={!erros.nome.valido}
              helperText={erros.nome.texto}
            />
          </Grid>

          <Grid item xs={6} pb={2}>
            <InputLabel>Email</InputLabel>
            <TextField
              id="email"
              type="email"
              name="email"
              required
              variant="outlined"
              fullWidth
              placeholder="Usuario@email.com"
              value={email}
              onChange={(event) => setEmail(event.target.value)}
              onBlur={(event) =>
                setErrorEmail(validations["email"](event.target.value, emails))
              }
              error={!errorEmail.valido}
              helperText={errorEmail.texto}
            />
          </Grid>
        </Grid>

        <Grid container spacing={2}>
          <Grid item xs={4} pb={2}>
            <InputLabel>Numero de celular</InputLabel>
            <InputMask
              mask="(99)99999-9999"
              value={numero}
              disabled={false}
              maskChar=""
              onChange={(event) =>
                setNumero(event.target.value.replace(/\D/g, ""))
              }
              onBlur={(event) => validation(numero, "numero")}
            >
              {() => (
                <TextField
                  id="numero"
                  variant="outlined"
                  fullWidth
                  required
                  placeholder="(11) 1111-1111"
                  error={!erros.numero.valido}
                  helperText={erros.numero.texto}
                />
              )}
            </InputMask>
          </Grid>
          <Grid item xs={8} pb={2}>
            <InputLabel>Observação do numero</InputLabel>
            <TextField
              id="obsNumero"
              fullWidth
              variant="outlined"
              type="text"
              placeholder="Digite uma observação sobre o numero de telefone"
              value={obsNumero}
              onChange={(event) => setObsNumero(event.target.value)}
            />
          </Grid>
        </Grid>

        <Button variant="contained" type="submit">
          Proximo
        </Button>
      </Container>
    </form>
  );
}

export default Pessoal;
