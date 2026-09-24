<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>Registro Enfermera / Elitec</title>
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
			<h1>Registro de Enfermera</h1>
			<form id="formEnfermera" method="post" novalidate >
				<div class="row" style="margin-top: 2%;">
					<div class="col-6">
						<label for="nombres">Nombres</label>
						<input type="text" class="form-control" id="nombres" name="nombres" placeholder="Ingrese los nombres" maxlength="50" required>
						<div class="invalid-feedback">Ingrese los nombres</div>
					</div>
					<div class="col-6">
						<label for="apellidos">Apellidos</label>
						<input type="text" class="form-control" id="apellidos" name="apellidos" placeholder="Ingrese los apellidos" maxlength="50" required>
						<div class="invalid-feedback">Ingrese los apellidos</div>
					</div>
				</div>
				<div class="row" style="margin-top: 2%;">
					<div class="col-3">
						<label for="dni">DNI</label>
						<input type="text" class="form-control" id="dni" name="dni" placeholder="Ingrese el DNI" maxlength="8" required>
						<div class="invalid-feedback">Ingrese el DNI</div>
					</div>
					<div class="col-3">
						<label for="fechaNacimiento">Fecha de Nacimiento</label>
						<input type="date" class="form-control" id="fechaNacimiento" name="fechaNacimiento" required>
						<div class="invalid-feedback">Ingrese la fecha de nacimiento</div>
					</div>
					<div class="col-3">
						<label for="especialidad">Especialidad</label>
						<input type="text" class="form-control" id="especialidad" name="especialidad" placeholder="Ingrese la especialidad" maxlength="50" required>
						<div class="invalid-feedback">Ingrese la especialidad</div>
					</div>
					<div class="col-3">
						<label for="telefono">Teléfono</label>
						<input type="text" class="form-control" id="telefono" name="telefono" placeholder="Ingrese el teléfono" maxlength="15" required>
						<div class="invalid-feedback">Ingrese el teléfono</div>
					</div>
				</div>
				<div class="row" style="margin-top: 2%;">
					<div class="col-4">
						<label for="turno">Turno</label>
						<select class="form-control" id="turno" name="turno" required>
							<option value="" selected disabled>Seleccione un turno</option>
							<option value="Mañana">Mañana</option>
							<option value="Tarde">Tarde</option>
							<option value="Noche">Noche</option>
						</select>
						<div class="invalid-feedback">Seleccione un turno</div>
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


				let form = $('#formEnfermera')[0];
		        if (form.checkValidity() === false) {
		            $(form).addClass('was-validated');
		            return;
		        }


		        $.ajax({
					url: 'registraEnfermeraAlias',
					type: 'POST',
					data: $(form).serialize(),
					success: function (response) {

						console.log('response >>> '+ response);
						//limpiar el formulario
						$('#formEnfermera')[0].reset();

						//limpiar las validaciones
						$('#formEnfermera').removeClass('was-validated');

						//enviar un mensaje de éxito al usuario en forma de div que dure 3 segundos
						$('#formEnfermera').prepend('<div class="alert alert-success" role="alert">'+ response.mensajeSalida +'</div>');
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