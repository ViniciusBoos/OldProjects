import {useState} from 'react'


function useValidation(validacoes){

    const initialState = createInitialState(validacoes);
    const [erros, setErros] = useState(initialState);

    function validation(value, campo) {

        const estado = initialState;
        estado[campo] = validacoes[campo](value);
        setErros(estado);
    }

    function valid(){
        for(let campo in erros) {
            if(!erros[campo].valido){
                return false;
            }
        }
        return true;
    }
    return[erros, validation, valid];
}


function createInitialState(validacoes) {
    const initialState = {}
    for(let camps in validacoes) {
        initialState[camps] = {valido: true, text: ""};
    }

    return initialState;
}

export default useValidation;