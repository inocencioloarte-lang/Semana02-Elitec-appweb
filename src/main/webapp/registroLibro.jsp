
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>Registro Libro / Elitec</title>
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
			<h1>Registro de Libro</h1>
			<form id="formLibro" method="post" novalidate >
				<div class="row" style="margin-top: 2%;">
					<div class="col-3">
						<label for="registro">Registro</label> 
						<input type="text" class="form-control" id="registro" name="registro" placeholder="Ingrese el registro" maxlength="30" required>
						<div class="invalid-feedback">Ingrese el registro</div>
					</div>
					<div class="col-9">
						<label for="titulo">Título</label> 
						<input type="text" class="form-control" id="titulo" name="titulo" placeholder="Ingrese el título" maxlength="30" required>
						<div class="invalid-feedback">Ingrese el título</div>
					</div>
				</div>
				<div class="row" style="margin-top: 2%;">	
					<div class="col-4">
						<label for="pais">País</label> 
						<input type="text" class="form-control" id="pais" name="pais" placeholder="Ingrese el país" maxlength="30" required>
						<div class="invalid-feedback">Ingrese el país</div>
					</div>
					<div class="col-4">
						<label for="autor">Autor</label> 
						<input type="text" class="form-control" id="autor" name="autor" placeholder="Ingrese el autor" maxlength="30" required>
						<div class="invalid-feedback">Ingrese el autor</div>
					</div>
					<div class="col-4">
						<label for="fecCreacion">Fecha de Creación</label> 
						<input type="date" class="form-control" id="fecCreacion" name="fecCreacion" required>
						<div class="invalid-feedback">Ingrese la Fecha de Creación</div>
					</div>
				</div>
				<div class="row justify-content-center" style="margin-top: 2%">
					<button class="btn btn-primary" id="btnRegistrar"style="width: 200px">Registrar</button>
				</div>
			</form>
		</div>
		
		<script type="text/javascript">
			$("#btnRegistrar").click(function(e) {
				console.log("click en registrar");		
				e.preventDefault(); //Evita que el formulario se envíe automáticamente
		
				
				let form = $('#formLibro')[0];
		        if (form.checkValidity() === false) {
		            $(form).addClass('was-validated');
		            return;
		        }
		
		     
		        $.ajax({
					url: 'registraLibroAlias',
					type: 'POST',
					data: $(form).serialize(),
					success: function (response) {
						
						console.log('response >>> '+ response);
						//limpiar el formulario
						$('#formLibro')[0].reset();
						
						//limpiar las validaciones
						$('#formLibro').removeClass('was-validated');
						
						//enviar un mensaje de éxito al usuario en forma de div que dure 3 segundos
						$('#formLibro').prepend('<div class="alert alert-success" role="alert">'+ response.mensajeSalida +'</div>');
						setTimeout(function () {
							$('.alert').remove();
						}, 3000);
					},
					error: function (xhr, status, error) {
						// Manejar errores aquí
						console.error('Error al registrar :', error);
					}
				});
			});
		</script>

</body>
</html>