import { TextField, Button, InputLabel, Grid } from "@mui/material";
import React, { useContext, useState } from "react";
import InputMask from "react-input-mask";
import ValidacaoContext from "../../context/validacaoContext";
import useValidation from "../../validation";
import inputNormalValue from "../../models/inputNormalValue";

function PessoaFisica({ onSent, dados, tipo }) {
  const [rg, setRg] = useState(inputNormalValue(dados.rg));

  const validations = useContext(ValidacaoContext);
  const [erros, validation, valid] = useValidation(validations);

  

  return (
    <form
      onSubmit={(event) => {
        event.preventDefault();
        if (valid()) {
          onSent({ rg, tipo });
        }
      }}
    >
      <Grid container pt={2} pb={2}>
        <InputLabel>RG</InputLabel>
        <InputMask
          mask="9999999-9"
          value={rg}
          disabled={false}
          maskChar=" "
          onChange={(event) => setRg(event.target.value.replace(/\D/g, ""))}
          onBlur={() => validation(rg, "rg")}
        >
          {() => (
            <TextField
              id="rg"
              name="rg"
              required
              variant="outlined"
              fullWidth
              placeholder="1111111-1"
              error={!erros.rg.valido}
              helperText={erros.rg.texto}
            />
          )}
        </InputMask>
      </Grid>

      <Button margin="normal" variant="contained" type="submit">
        Proximo
      </Button>
    </form>
  );
}

export default PessoaFisica;
