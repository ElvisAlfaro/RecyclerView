<?php
	$server = "localhost";
	$user = "root";
	$password = "";
	$bd = "dragonball_bd";
	
	$conexion = mysqli_connect($server, $user, $password, $bd);
	if (!$conexion){ 
		die('Error de Conexión: ' . mysqli_connect_errno());	
	}
	
	$query = "SELECT * FROM item";
	$resultado = mysqli_query($conexion, $query);
	
	while($row = mysqli_fetch_assoc($resultado)){
            $datos[] = array_map('utf8_encode', $row);
    }
    mysqli_close($conexion);

    $json_string = json_encode($datos, JSON_PRETTY_PRINT);

    echo $json_string;
?>