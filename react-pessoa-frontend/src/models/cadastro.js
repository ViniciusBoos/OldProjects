function validateRG(rg) {
  if (rg.replace(/\D/g, "").length !== 8) {
    return { valido: false, texto: "RG deve conter 8 digitos" };
  }
  return { valido: true, texto: "" };
}

function validateCnae(cnae) {
  if (cnae.replace(/\D/g, "").length !== 5) {
    return { valido: false, texto: "CNAE deve conter 5 digitos" };
  }
  return { valido: true, texto: "" };
}

function validateTelefone(telefone) {
  if (telefone.replace(/\D/g, "").length !== 11) {
    return {
      valido: false,
      texto: "Numero de Telefone deve conter 11 digitos",
    };
  }
  return { valido: true, texto: "" };
}

function validateCep(cep) {
  if (cep.replace(/\D/g, "").length !== 8) {
    return {
      valido: false,
      texto: "Cep deve conter 8 digitos",
    };
  }
  return { valido: true, texto: "" };
}

function validateSelect(endereco) {
  if (endereco === 0) {
    return { valido: false, texto: "Por favor selecione uma opção valida" };
  }
  return { valido: true, texto: "" };
}

function validateSize(size) {
  if (size.length < 3) {
    return { valido: false, texto: "Minimo de 3 digitos" };
  }

  if (size.length > 72) {
    return { valido: false, texto: "Maximo de 72 digitos" };
  }

  return { valido: true, texto: "" };
}

function validateEmail(email, emailsRegistrados) {
  for(const emailRegistrado of emailsRegistrados){
    if(emailRegistrado === email) {
      return { valido: false, texto: "Email ja registrado" };
    }
  }
  return { valido: true, texto: "" };
}

export {
  validateRG,
  validateTelefone,
  validateCnae,
  validateSize,
  validateSelect,
  validateCep,
  validateEmail,
};
