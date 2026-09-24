<!DOCTYPE html>
<html>
<head>
<head>
	<meta charset="UTF-8">
	<title>Registro Libro Elitec</title>
	<script src="js/bootstrap.js" type="text/javascript"></script>
	<script src="js/bootstrap.bundle.js" type="text/javascript"></script>
	<script src="js/bootstrap.esm.js" type="text/javascript"></script>
	<script src="js/jquery-4.0.0.min.js" type="text/javascript"></script>
	
	<link href="css/bootstrap.css" rel="stylesheet">
	<link href="css/bootstrap-grid.css" rel="stylesheet">
	<link href="css/bootstrap-reboot.css" rel="stylesheet">
	<link href="css/bootstrap-utilities.css" rel="stylesheet">
</head>
<body>
	<div class="container">
		<h1>Lista de Libro por título</h1>
		
		<div class="row" style="margin-top: 2%;">
			<div class="col-3">
					<label for="titulo">Título</label> 
			</div>
			<div class="col-6">
					<input type="text" class="form-control" id="titulo" name="titulo" placeholder="Ingrese el título" maxlength="30">
			</div>
			<div class="col-3">
                    <button class="btn btn-primary" id="btnBuscar"style="width: 200px">Buscar</button>
            </div>        
		</div>
		
		<div class="row" style="margin-top: 2%;">
            <div class="col-12">
                <table class="table table-striped">
                    <thead>
                        <tr><th>Código</th>
                            <th>Registro</th>
                            <th>Título</th>
                            <th>País</th>
                            <th>Autor</th>
                            <th>Fecha de Creación</th>
                        </tr>
                    </thead>
                    <tbody id="tablaLibros">
 
                    </tbody>
                </table>
          </div>
			
	</div>
</body>
</html>