import React, { useState } from "react";
import {
  Container,
  TextField,
  Button,
  Grid,
  InputLabel,
  Typography,
} from "@mui/material";
import InputMask from "react-input-mask";

function ContatoForm({
  onSent,
  close,
  getContatos,
  contatoUpdate,
  startUpdate,
  updateContato,
  getContatoId,
}) {
  const [numero, setNumero] = useState(updateForm(contatoUpdate.numero));
  const [nome, setNome] = useState(updateForm(contatoUpdate.nome));
  const [email, setEmail] = useState(updateForm(contatoUpdate.email));
  const [data, setData] = useState(updateForm(contatoUpdate.data));
  const [id] = useState(getId(contatoUpdate.id));

  function getId(dadoContato) {
    if (startUpdate === 1) {
      return dadoContato;
    }
    return getContatoId;
  }

  function updateForm(dadoContato) {
    if (startUpdate === 1) {
      return dadoContato;
    }
    return "";
  }

  return (
    <Container>
      <form
        onSubmit={(event) => {
          event.preventDefault();
          if (startUpdate === 1) {
            updateContato({
              id,
              nome,
              numero,
              email,
              data,
            });
          } else {
            onSent({ id, nome, numero, email, data});
          }
          close();
          getContatos(1);
        }}
      >
        <Grid justifyContent="center" container>
          <Typography variant="h3">Contato Form</Typography>{" "}
        </Grid>
        <Grid container>
          <Grid item xs={12} pb={2} pt={2}>
            <InputLabel>Nome Completo</InputLabel>
            <TextField
              id="nome"
              type="text"
              placeholder="Fulano da silva"
              variant="outlined"
              fullWidth
              value={nome}
              required
              onChange={(event) => setNome(event.target.value)}
            />
          </Grid>
        </Grid>

        <Grid container>
          <Grid item xs={12} pb={2}>
            <InputLabel>Email</InputLabel>
            <TextField
              id="email"
              type="email"
              variant="outlined"
              placeholder="usuario@email.com"
              fullWidth
              value={email}
              onChange={(event) => setEmail(event.target.value)}
            />
          </Grid>
        </Grid>

        <Grid container spacing={2}>
          <Grid item xs={6} pb={2}>
            <InputLabel>Numero</InputLabel>
            <InputMask
              mask="(99)99999-9999"
              value={numero}
              disabled={false}
              maskChar=" "
              onChange={(event) => setNumero(event.target.value)}
            >
              {() => (
                <TextField
                  id="numero"
                  variant="outlined"
                  fullWidth
                  placeholder="(11) 1111-1111"
                />
              )}
            </InputMask>
          </Grid>
          <Grid item xs={6} pb={2}>
            <InputLabel>Data de nascimento</InputLabel>
            <InputMask
              mask="99/99/9999"
              value={data}
              disabled={false}
              maskChar=""
              onChange={(event) => setData(event.target.value)}
            >
              {() => (
                <TextField
                  id="dataNascimento"
                  variant="outlined"
                  fullWidth
                  placeholder="11/11/1111"
                  value={data}
                />
              )}
            </InputMask>
          </Grid>
        </Grid>
        <Grid container></Grid>

        <Grid pt={1}>
          <Button fullWidth variant="contained" type="submit">
            Cadastrar Contato
          </Button>
        </Grid>
      </form>
    </Container>
  );
}

export default ContatoForm;
