<?php

include('sql.php');

class Manager{

    //$listaArrayCarro, $listaArrayPecas,$listaArrayManutencao

        function managerUsuarios($nome, $email, $listaCarrosJson, $listaPecasJson , $listaManutencaoJson){
                
                $user = $this->consultarUsuario($email);

                if($user==null){

                   $this->salvarUsuario($nome,$email);

                   $user  = $this->consultarUsuario($email);

                   $this->consultarListaManutencao($listaManutencaoJson,$user[0]["id"]);

                    $this->consultarListaCarros($listaCarrosJson, $user[0]["id"]);

                    $this->consultarListaPecas($listaPecasJson, $user[0]["id"]);

                    return  $user;
                }else{

                    $this->consultarListaManutencao($listaManutencaoJson,$user[0]["id"]);

                    $this->consultarListaCarros($listaCarrosJson, $user[0]["id"]);

                    $this->consultarListaPecas($listaPecasJson, $user[0]["id"]);

          
                   return $user;
                    }
    
        }
        function salvarUsuario($nome, $email){
            $sql = new Sql();
           $sql->salvarUsuario($nome, $email);
        
        }
        function consultarUsuario($email){
            $sql = new Sql();
            
            $user = $sql->consultarUsuario($email);

            return  $user;
        }

        //fim das funçoes de usuario



        // inicio das funções de carro

        function consultarListaCarros($listaCarrosJson, $idUsuario){
            $sql = new Sql();

            $listaCarros = $sql->consultarListaCarros($idUsuario);

            $tamanhoListaCarros = count($listaCarros);


            if($tamanhoListaCarros==0){
               
                $this->salvarListaCarros($listaCarrosJson,$idUsuario);
            }else{

                $this->deletarListaCarros($tamanhoListaCarros, $listaCarros);  

                $this->salvarListaCarros($listaCarrosJson,$idUsuario);
            }

        }

        
        function salvarListaCarros($listaCarro,$idUsuario){
            $sql = new Sql();

            $size = count($listaCarro);

            if($size>0){
                for($i=0;$i<$size;$i++){
                    $sql->inserirCarro($listaCarro[$i],$idUsuario);
                }
               

            }

        }

        function deletarListaCarros($tamanhoListaCarros, $listaCarros){
            $sql = new Sql();

            for($i=0;$i<$tamanhoListaCarros;$i++){

                $sql->deletarListaCarros($listaCarros[$i]->id);
            }

        }


        // fim das funções de carro



        /// início das funcões de pecas

        function consultarListaPecas($listaPecasJson, $idUsuario){
            $sql = new Sql();

            $listaPecas = $sql->consultarListaPecas($idUsuario);

            $tamanhoListaPecas = count( $listaPecas);


            if($tamanhoListaPecas==0){
               
                $this->salvarListaPecas($listaPecasJson,$idUsuario);
            }else{

                $this->deletarPecas($tamanhoListaPecas, $listaPecas);  

                $this->salvarListaPecas($listaPecasJson,$idUsuario);
            }

        }

        function salvarListaPecas($listaPecas,$idUsuario){
            $sql = new Sql();

            $size = count($listaPecas);

            if($size>0){
                for($i=0;$i<$size;$i++){
                    $sql->inserirPecas($listaPecas[$i],$idUsuario);
                }
               

            }

        }
        function deletarPecas($tamanhoListaPecas,$listaPecas){
            $sql = new Sql();
           
            for($i=0;$i<$tamanhoListaPecas;$i++){

                $sql->deletarPecas($listaPecas[$i]->id);
            }

        }

        // fim das funções de pecas











        // inicio das funcoes de manutencao

        function consultarListaManutencao($listaManutencaoJson,$idUsuario){
           $sql = new Sql();
        
           $manutencoes = $sql->consultarListaManutencao($idUsuario);

           $tamanhoManutencao =count($manutencoes);


            if($tamanhoManutencao==0){
              
                return $this->salvarListaManutencoes($listaManutencaoJson,$idUsuario);
            }else{

                $this->deletarManutencao($tamanhoManutencao, $manutencoes);  

                return $this->salvarListaManutencoes($listaManutencaoJson,$idUsuario);
            }
        }


        function salvarListaManutencoes($listaManutencaoJson, $idUsuario){
            $sql = new Sql();

           
          $size = count($listaManutencaoJson);
          $newconta= $size;

 
            for($i=0;$i<$size;$i++){
                $sql->inserirManutencao($listaManutencaoJson[$i],$idUsuario);
            
            }
           return $newconta;
        }

        function deletarManutencao($tamanhoManutencao, $manutencoes){
            $sql = new Sql();
            for($i=0;$i<$tamanhoManutencao;$i++){

                $sql->deletarManutencao($manutencoes[$i]->id);
            }
            
        }

        //fim das funcoes de manutencao


        // inicio metodos de donwlodas

        function downUsuario($email){
            $sql = new Sql();
            $user = $this->consultarUsuario($email);
            return $user;

        }
        function downListaCarro($idUsuario){
            $sql = new Sql();
    
            $listaCarros = $sql->consultarListaCarros($idUsuario);

            return $listaCarros;

        }
        function downListaManutencao($idUsuario){
            $sql = new Sql();
        
            $listaManutencao = $sql->consultarListaManutencao($idUsuario);

            return $listaManutencao;

        }
        function downListaPecas($idUsuario){
            $sql = new Sql();

            $listaPecas = $sql->consultarListaPecas($idUsuario);

            return $listaPecas;

        }
}    


?>