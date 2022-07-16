import React, { useEffect, useState } from "react";
import {
  Container,
  Grid,
  Box,
  Button,
  Table,
  IconButton,
  TableContainer,
  TableHead,
  TableRow,
  TableCell,
  TableBody,
  TextField,
  Dialog,
  Typography,
} from "@mui/material";
import DeleteIcon from "@mui/icons-material/Delete";
import AddBoxIcon from "@mui/icons-material/AddBox";
import EditIcon from "@mui/icons-material/Edit";
import SearchIcon from "@mui/icons-material/Search";
import Cabecalho from "../cabecalho";
import { buscaDirecionada, deletePessoa } from "../../api/api";

function Home({ updatePessoa, novaPessoa }) {
  const [pessoas, setPessoas] = useState([]);
  const [nome, setNome] = useState("");
  const [showDialog, setShow] = React.useState(false);
  const [pessoaIdDelete, setPessoaIdDelete] = useState();
  const [startDelete, SetStartDelete] = useState(0);

  const handleOpenShow = () => setShow(true);
  const handleCloseShow = () => setShow(false);

  const deleteIcon = (id) => {
    return (
      <IconButton
        onClick={() => {
          handleOpenShow();
          setPessoaIdDelete(id);
        }}
      >
        <DeleteIcon />
      </IconButton>
    );
  };

  useEffect(() => {
    if (startDelete === 1) {
      deletePessoa("http://localhost:8080/deletePessoa", pessoaIdDelete);
      setPessoas([])
      SetStartDelete(0);
    }
  }, [startDelete, pessoaIdDelete, nome]);

  const editIcon = (pessoa) => {
    return (
      <IconButton
        onClick={() => {
          updatePessoa(pessoa);
        }}
      >
        <EditIcon />
      </IconButton>
    );
  };

  return (
    <>
      <Cabecalho titulo={"HOME"} />
      <Dialog open={showDialog} onClose={handleCloseShow}>
        <Container>
          <Grid container justifyContent="center" pb={2} pt={2} pl={2} pr={2}>
            <Typography>Tem certeza que dejesa deletar?</Typography>
          </Grid>
          <Grid container pb={2} spacing={2} justifyContent="center">
            <Grid item xs={6} >
              <Button
                fullWidth
                variant="contained"
                onClick={() => {
                  SetStartDelete(1);
                  handleCloseShow();
                }}
              >
                Sim
              </Button>
            </Grid>
            <Grid item xs={6}>
              <Button
                fullWidth
                variant="contained"
                onClick={() => {
                  handleCloseShow();
                }}
              >
                Nâo
              </Button>
            </Grid>
          </Grid>
        </Container>
      </Dialog>
      <Container>
        <Grid container pt={2}>
          <Box width="100%" style={{ border: "1px solid rgba(0,0,0,0.2)" }}>
            <Box pb={2} pt={2} style={{ border: "1px solid rgba(0,0,0,0.2)" }}>
              <Grid container pl={3}>
                <Grid item xs={3} pt={1}>
                  <Button
                    endIcon={<AddBoxIcon />}
                    variant="contained"
                    onClick={novaPessoa}
                  >
                    Novo
                  </Button>
                </Grid>
                <Grid item>
                  <TextField
                    value={nome}
                    label="Buscar por nome"
                    name="name"
                    onChange={(event) => {
                      setNome(event.target.value);
                    }}
                  />
                </Grid>
                <Grid item pt={1} pl={2}>
                  <Button
                    endIcon={<SearchIcon />}
                    variant="contained"
                    onClick={() => {
                      buscaDirecionada(
                        "http://localhost:8080/pessoas",
                        nome,
                        setPessoas
                      );
                    }}
                  >
                    Buscar
                  </Button>
                </Grid>
              </Grid>
            </Box>
            <Box>
              <Grid item xs={12}>
                <TableContainer>
                  <Table>
                    <TableHead>
                      <TableRow>
                        <TableCell width="5%" align="right">
                          #
                        </TableCell>
                        <TableCell width="35%" align="left">
                          Nome
                        </TableCell>
                        <TableCell width="40%" align="left">
                          Email
                        </TableCell>
                        <TableCell align="left" width="20%">
                          Edit
                        </TableCell>
                      </TableRow>
                    </TableHead>
                    <TableBody>
                      {pessoas.map((pessoa, index) => {
                        return (
                          <TableRow key={index}>
                            <TableCell align="right">{index}</TableCell>
                            <TableCell align="left">
                              {pessoa.pessoa.nome}
                            </TableCell>
                            <TableCell align="left">
                              {pessoa.pessoa.email}
                            </TableCell>
                            <TableCell component="th" scope="row">
                              {deleteIcon(pessoa.pessoa.idPessoa)}
                              {editIcon(pessoa)}
                            </TableCell>
                          </TableRow>
                        );
                      })}
                    </TableBody>
                  </Table>
                </TableContainer>
              </Grid>
            </Box>
          </Box>
        </Grid>
      </Container>
    </>
  );
}

export default Home;
