import React, { useEffect, useState } from "react";
import {
  Button,
  Container,
  Box,
  Dialog,
  Grid,
  IconButton,
  Table,
  TableContainer,
  TableHead,
  TableRow,
  TableCell,
  TableBody,
} from "@mui/material";
import DeleteIcon from "@mui/icons-material/Delete";
import AddBoxIcon from "@mui/icons-material/AddBox";
import EditIcon from "@mui/icons-material/Edit";
import CloseIcon from "@mui/icons-material/Close";
import ContatoForm from "./form";

function Contato({
  onSent,
  dados,
  updateContato,
  deletarContato,
  getContatoId,
  sendDados,
  changePage,
}) {
  const [show, setShow] = React.useState(false);
  const handleOpen = () => setShow(true);
  const handleClose = () => setShow(false);
  const [contatos, setContatos] = useState([]);
  const [getContatos, setGetContatos] = useState(1);
  const [contatoUpdate, setContatoUpdate] = useState([]);
  const [startUpdate, setStartUpdate] = useState(0);
  const [deleteIndex, setDeleteIndex] = useState();

  useEffect(() => {
    if (getContatos === 1) {
      setContatos([...dados]);
      setGetContatos(0);
    }
  }, [contatos, dados, getContatos]);

  const style = {
    padding: "10px",
  };

  const deleteIcon = (index) => {
    return (
      <IconButton
        onClick={() => {
          deletarContato(index);
          setGetContatos(1);
        }}
      >
        <DeleteIcon />
      </IconButton>
    );
  };

  const editIcon = (index, contato) => {
    return (
      <IconButton
        onClick={() => {
          setGetContatos(1);
          setDeleteIndex(index);
          setContatoUpdate({ ...contato });
          setStartUpdate(1);
          handleOpen();
        }}
      >
        <EditIcon />
      </IconButton>
    );
  };

  return (
    <Container>
      <Dialog open={show} onClose={handleClose} fullWidth>
        <Grid container justifyContent="flex-end">
          <Grid item>
            <IconButton variant="contained" onClick={handleClose}>
              {" "}
              <CloseIcon />
            </IconButton>
          </Grid>
        </Grid>
        <Box style={style}>
          <ContatoForm
            onSent={onSent}
            close={handleClose}
            getContatos={setGetContatos}
            contatoUpdate={contatoUpdate}
            startUpdate={startUpdate}
            index={deleteIndex}
            updateContato={updateContato}
            getContatoId={getContatoId}
          />
        </Box>
      </Dialog>

      <Grid container>
        <Box width="100%" style={{ border: "1px solid rgba(0,0,0,0.2)" }}>
          <Box pb={2} pt={2} style={{ border: "1px solid rgba(0,0,0,0.2)" }}>
            <Grid pl={2}>
              <Button
                endIcon={<AddBoxIcon />}
                variant="contained"
                onClick={() => {
                  handleOpen();
                  setStartUpdate(0);
                }}
              >
                Novo
              </Button>
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
                      <TableCell width="40%" align="left">
                        Nome
                      </TableCell>
                      <TableCell width="40%" align="left">
                        Email
                      </TableCell>
                      <TableCell align="left" width="15%">
                        Edit
                      </TableCell>
                    </TableRow>
                  </TableHead>
                  <TableBody>
                    {contatos.map((contato, index) => {
                      return (
                        <TableRow key={index}>
                          <TableCell align="right">{index}</TableCell>
                          <TableCell align="left">{contato.nome}</TableCell>
                          <TableCell align="left">{contato.email}</TableCell>
                          <TableCell component="th" scope="row">
                            {deleteIcon(index)}
                            {editIcon(index, contato)}
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

      <Grid container pt={3}>
        <Button
          variant="contained"
          onClick={() => {
            sendDados(1);
            changePage();
          }}
        >
          Finalizar cadastro
        </Button>
      </Grid>
    </Container>
  );
}

export default Contato;
