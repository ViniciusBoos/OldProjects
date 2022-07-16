import { TextField, Button, Grid, InputLabel } from "@mui/material";
import React, { useContext, useState } from "react";
import InputMask from "react-input-mask";
import ValidacaoContext from "../../context/validacaoContext";
import useValidation from "../../validation";
import inputNormalValue from "../../models/inputNormalValue";

function PessoaJuridica({ onSent, dados, tipo }) {
  const [cnae, setCnae] = useState(inputNormalValue(dados.cnae));

  const validations = useContext(ValidacaoContext);
  const [erros, validation, valid] = useValidation(validations);

  return (
    <form
      onSubmit={(event) => {
        event.preventDefault();
        if (valid()) {
          onSent({ cnae, tipo });
        }
      }}
    >
      <Grid container pt={2} pb={2}>
        <InputLabel>CNAE</InputLabel>
        <InputMask
          mask="9999-9"
          value={cnae}
          disabled={false}
          maskChar=" "
          onChange={(event) => setCnae(event.target.value.replace(/\D/g, ""))}
          onBlur={() => validation(cnae, "cnae")}
        >
          {() => (
            <TextField
              id="cnae"
              name="cnae"
              required
              variant="outlined"
              fullWidth
              placeholder="1111-1"
              error={!erros.cnae.valido}
              helperText={erros.cnae.texto}
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

export default PessoaJuridica;
