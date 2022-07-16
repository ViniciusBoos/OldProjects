import { Select, Container, MenuItem, Grid, InputLabel } from "@mui/material";
import React, { useState } from "react";
import PessoaFisica from "../pessoaFisica";
import PessoaJuridica from "../pessoaJuridica";

function TipoPessoa({ onSent, dados }) {
  const [tipo, setTipo] = useState(selectTipo(dados.tipo));

  function selectTipo(dados){
    if(dados === "PESSOA_FISICA") {
      return 0;
    }
    if(dados === "PESSOA_JURIDICA") {
      return 1;
    }
    return 0;
  }

  const TipoForm = [
    <PessoaFisica onSent={onSent} dados={dados} tipo={tipo}/>,
    <PessoaJuridica onSent={onSent} dados={dados} tipo={tipo}/>,
  ];

  return (
    <Container>
      <Grid pt={2}>
        <InputLabel>Tipo de pessoa</InputLabel>
        <Select
          margin="dense"
          defaultValue={0}
          className="mb-3"
          onChange={(event) => {
            setTipo(event.target.value);
          }}
        >
          <MenuItem value={0}>Pessoa fisica</MenuItem>
          <MenuItem value={1}>Pessoa Juridica</MenuItem>
        </Select>
      </Grid>

      {TipoForm[tipo]}
    </Container>
  );
}

export default TipoPessoa;
