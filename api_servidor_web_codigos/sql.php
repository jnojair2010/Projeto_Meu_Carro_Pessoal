<?php

include("conexao.php");


class Sql{

        function consultarUsuario($email){
            $conexao = new Conexao();
        
            $con=  $conexao->getConexao();

            $sql = "SELECT * FROM  usuarios WHERE email=:email";

            $consultar = $con->prepare($sql);
                $consultar->bindValue(":email",$email);
                $consultar->execute();
		        $linha=$consultar->fetchAll(PDO::FETCH_ASSOC);            
                return $linha;
        }

        function salvarUsuario($nome, $email){
            $conexao = new Conexao();
            $con=  $conexao->getConexao();

            $sql="INSERT INTO usuarios (nome,email) VALUES (:nome,:email)";

            $inserir = $con->prepare($sql);
            $inserir->bindValue(":nome",$nome);
            $inserir->bindValue(":email",$email);
            $inserir->execute();
            
        }





        // início funcoes sql pecas
        function consultarListaPecas($idUsuario){
            $conexao = new Conexao();
            $con=  $conexao->getConexao();

            
            $sql = "SELECT * FROM pecas WHERE idUsuarios=?";
         	$consultar = $con->prepare($sql);
            $consultar->execute([$idUsuario]);
		    $linha=$consultar->fetchAll(PDO::FETCH_OBJ);            
            return $linha;

        }
        function inserirPecas($peca, $idUsuario){
            $conexao = new Conexao();
            $con=  $conexao->getConexao();

            $sql="INSERT INTO pecas (idApp, idManutencao, idCarro, nome, marca, strData, referencia, kmValidade, kmInstalacao, quantidade, preco, cupom, localCompra, idUsuarios) VALUES
             (:idApp, :idManutencao, :idCarro, :nome, :marca, :strData, :referencia, :kmValidade, :kmInstalacao, :quantidade, :preco, :cupom, :localCompra, :idUsuarios)";

            $inserir = $con->prepare($sql);
            $inserir->bindValue(":idApp",$peca->idApp);
            $inserir->bindValue(":idManutencao",$peca->idManutencao);
            $inserir->bindValue(":idCarro",$peca->idCarro);
            $inserir->bindValue(":nome",$peca->nome);
            $inserir->bindValue(":marca",$peca->marca);
            $inserir->bindValue(":strData",$peca->data);
            $inserir->bindValue(":referencia",$peca->referencia);
            $inserir->bindValue(":kmValidade",$peca->kmValidade);
            $inserir->bindValue(":kmInstalacao",$peca->kmInstalacao);
            $inserir->bindValue(":quantidade",$peca->quantidade);
            $inserir->bindValue(":preco",$peca->valor);
            $inserir->bindValue(":cupom",$peca->cupom);
            $inserir->bindValue(":localCompra",$peca->local);
            $inserir->bindValue(":idUsuarios",$idUsuario);
            $inserir->execute();

        }
        function deletarPecas($idPecas){

            $conexao = new Conexao();
            $con=  $conexao->getConexao();
            $sql =  "DELETE FROM pecas WHERE id=?";
            $delet = $con->prepare($sql);
            $delet->execute([$idPecas]);

        }
        // fim funções sql pecas






        // a partir daqui sqls de manutencao

        function consultarListaManutencao($idUsuario){
            $conexao = new Conexao();
        
            $con=  $conexao->getConexao();

            $sql = "SELECT * FROM manutencao WHERE idUsuarios=?";
         	$consultar = $con->prepare($sql);
            $consultar->execute([$idUsuario]);
		    $linha=$consultar->fetchAll(PDO::FETCH_OBJ);            
            return $linha;
        }
    
        function inserirManutencao($manutencao, $idUsuario){
            $conexao = new Conexao();
            $con=  $conexao->getConexao();
            $sql="INSERT INTO manutencao (strData, tipo, idCarro, kmAtual, nome, localManutencao, nomeMecanico, preco, kmProximaTroca, cupom, idApp, idUsuarios) VALUES
             (:strData, :tipo, :idCarro, :kmAtual, :nome, :localManutencao, :nomeMecanico, :preco, :kmProximaTroca, :cupom, :idApp, :idUsuarios)";

            $inserir = $con->prepare($sql);
            $inserir->bindValue(":strData",$manutencao->data);
            $inserir->bindValue(":tipo",$manutencao->tipo);
            $inserir->bindValue(":idCarro",$manutencao->idCarro);
            $inserir->bindValue(":kmAtual",$manutencao->km);
            $inserir->bindValue(":nome",$manutencao->nome);
            $inserir->bindValue(":localManutencao",$manutencao->local);
            $inserir->bindValue(":nomeMecanico",$manutencao->mecanico);
            $inserir->bindValue(":preco",$manutencao->valorPago);
            $inserir->bindValue(":kmProximaTroca",$manutencao->kmProximaTroca);
            $inserir->bindValue(":cupom",$manutencao->cupom);
            $inserir->bindValue(":idApp",$manutencao->idApp);
            $inserir->bindValue(":idUsuarios",$idUsuario);
            $inserir->execute();

        }

        function deletarManutencao($idManutencao){
            $conexao = new Conexao();
            $con=  $conexao->getConexao();
            $sql =  "DELETE FROM manutencao WHERE id=?";
            $delet = $con->prepare($sql);
            $delet->execute([$idManutencao]);
        }

        // fim das sqls de manutencao 







        // início das funções sql de carros

        function consultarListaCarros($idUsuario){
            $conexao = new Conexao();
            $con=  $conexao->getConexao();
        
            $sql = "SELECT * FROM carros WHERE idUsuarios=?";
         	$consultar = $con->prepare($sql);
            $consultar->execute([$idUsuario]);
		    $linha=$consultar->fetchAll(PDO::FETCH_OBJ);            
            return $linha;
           

        }
        
        function inserirCarro($carro, $idUsuario){
            $conexao = new Conexao();
        
            $con=  $conexao->getConexao();


            $sql="INSERT INTO carros (fabricante, modelo, nome, cor, ano, placa, idApp,idUsuarios) VALUES
             (:fabricante, :modelo, :nome, :cor, :ano, :placa, :idApp, :idUsuarios)";

            $inserir = $con->prepare($sql);
            $inserir->bindValue(":fabricante",$carro->fabricante);
            $inserir->bindValue(":modelo",$carro->modelo);
            $inserir->bindValue(":nome",$carro->nomecarro);
            $inserir->bindValue(":cor",$carro->cor);
            $inserir->bindValue(":ano",$carro->ano);
            $inserir->bindValue(":placa",$carro->placa);
            $inserir->bindValue(":idApp",$carro->idApp);
            $inserir->bindValue(":idUsuarios",$idUsuario);
 
            $inserir->execute();

        }

        function deletarListaCarros($id){
            $conexao = new Conexao();
            $con=  $conexao->getConexao();
            $sql =  "DELETE FROM carros WHERE id=?";
            $delet = $con->prepare($sql);
            $delet->execute([$id]);
        }
        
        // fim das funções sql de carros

        
    }


?>