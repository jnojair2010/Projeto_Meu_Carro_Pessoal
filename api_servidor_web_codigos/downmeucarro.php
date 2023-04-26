<?php
include('manager.php');

$email;
$solicitacao ;
$idUsuario;

if(isset($_GET['email'])){
    $email = $_GET['email'];
}else{
    $email = 0;
}

if(isset($_GET['solicitacao'])){
    $solicitacao = $_GET['solicitacao'];
}
if(isset($_GET['idUsuario'])){
    $idUsuario = $_GET['idUsuario'];
}





$manager = new Manager();

if($solicitacao == "solicitarUsuario"){

    echo json_encode($manager->downUsuario($email));

}elseif($solicitacao == "solicitarListaCarro"){

    echo json_encode($manager->downListaCarro($idUsuario));

}elseif($solicitacao == "solicitarListaManutencao"){

    echo json_encode($manager->downListaManutencao($idUsuario));
   

}elseif($solicitacao == "solicitarListaPecas"){
    echo json_encode($manager->downListaPecas($idUsuario));

}

?>