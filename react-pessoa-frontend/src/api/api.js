import axios from "axios";

export const api = axios.create({
  baseURL: "http://localhost:8080",
});

export const busca = async (url, setDado) => {
  const resposta = await api.get(url).catch(function (response) {
    alert(response);
  });;
  setDado(resposta.data);
};

export const buscaDirecionada = async (url, param, setDado) => {
  const params = new URLSearchParams([["codigo", param]]);

  await axios.get(`${url}`, { params }).then(function (response) {
    setDado(response.data);
  }).catch(function (response) {
    alert(response);
  });
};

export const send = async (url, data) => {
  const dadosEnvio = JSON.stringify({ ...data });
  const headers = {
    "Content-Type": "application/json",
  };
  await axios.post(url, dadosEnvio, { headers }).catch(function (response) {
    alert(response);
  });
};

export const deletePessoa = async (url, id) => {

  await axios.delete(url, {data: {id}, headers: {"Content-Type": "application/json"}})
  .catch(function (response) {
    alert(response);
  });
}

export const sendUpdatePessoa = async (url, dados) => {
  const dadosEnvio = JSON.stringify({ ...dados });
  const headers = {
    "Content-Type": "application/json",
  };
  console.log(dados);
  await axios.put(url, dadosEnvio, {headers})
  .catch(function (response) {
    alert(response);
  });;
}