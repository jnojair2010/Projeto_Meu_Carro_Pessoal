<?php
include('manager.php');

header('Content-Type: application/json; charset=utf-8');

//$email= $_GET['email'];
//$nome = $_GET['nome'];

$nome = $_POST['nome'];
$email= $_POST['email'];
$objsManutencao = $_POST['objsManutencao'];
$objsCarros = $_POST['objsCarros'];
$objsPecas = $_POST['objsPecas'];

$listaManutencaoJson = json_decode($objsManutencao);
$listaCarrosJson = json_decode($objsCarros);
$listaPecasJson = json_decode($objsPecas);


//if(count($listaCarrosJson)>0){

    $manager = new Manager();
    $usuario = $manager->managerUsuarios($nome, $email, $listaCarrosJson, $listaPecasJson , $listaManutencaoJson);
    $user = $usuario[0];
    

    $array = [
        "id"=>'1',
        "nome"=>$user["nome"],
        "email"=>$user["email"],
        "resultado"=>"salvo "
    ];

   echo json_encode($array);

//}
//, $objsCarros, $objsPecas, $objsManutencao
// $listaManutencaoJson[1]->nome

?>

