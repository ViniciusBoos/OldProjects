import { Grid } from "@mui/material";
import React from "react";
import styled from "styled-components";

const Faixa = styled.header`
  background-color: green;
  box-shadow: var(--sombra);
  display: grid;
  align-items: center;
  height: 6rem;
`;
const Titulo = styled.h1`
  font-family: "Oswald", sans-serifsssss;
  font-size: 2.5rem;
  color: white;
  text-align: center;
`;

function Cabecalho({ titulo }) {
  return (
    <Grid pb={1}>
      <Faixa>
        <Titulo>{titulo}</Titulo>
      </Faixa>
    </Grid>
  );
}

export default Cabecalho;
