<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Lista Enfermera Elitec</title>
	<script src="js/bootstrap.js" type="text/javascript"></script>
	<script src="js/bootstrap.bundle.js" type="text/javascript"></script>
	<script src="js/bootstrap.esm.js" type="text/javascript"></script>
	<script src="js/jquery-4.0.0.min.js" type="text/javascript"></script>
	<script src="js/datatables.js" type="text/javascript"></script>

	<link href="css/bootstrap.css" rel="stylesheet">
	<link href="css/bootstrap-grid.css" rel="stylesheet">
	<link href="css/bootstrap-reboot.css" rel="stylesheet">
	<link href="css/bootstrap-utilities.css" rel="stylesheet">
	<link href="css/datatables.css" rel="stylesheet">
</head>
<body>
	<div class="container">
		<h1>Lista de Enfermera por nombre</h1>

		<div class="row" style="margin-top: 2%;">
			<div class="col-3">
					<label for="nombres">Nombres</label>
			</div>
			<div class="col-6">
					<input type="text" class="form-control" id="nombres" name="nombres" placeholder="Ingrese el nombre" maxlength="50">
			</div>
			<div class="col-3">
                    <button class="btn btn-primary" id="btnBuscar"style="width: 200px">Buscar</button>
            </div>
		</div>

		<div class="row" style="margin-top: 2%;">
            <div class="col-12">
                <table class="table table-striped" id="id_table">
                    <thead>
                        <tr><th>Código</th>
                            <th>Nombres</th>
                            <th>Apellidos</th>
                            <th>DNI</th>
                            <th>Fecha de Nacimiento</th>
                            <th>Especialidad</th>
                            <th>Teléfono</th>
                            <th>Turno</th>
                        </tr>
                    </thead>
                    <tbody >

                    </tbody>
                </table>
          </div>
        </div>

	</div>

<script type="text/javascript">

$("#btnBuscar").click(function (e) {

	var varNombres = $("#nombres").val();
	console.log(">>> nombres: " , varNombres);

	$.ajax({
		url: "listaEnfermeraPorNombre",
		type: "GET",
		data: {nombres: varNombres},
		success: function (response) {
			console.log(">>> response: " , response);
			agregarGrilla(response);
		},
		error: function () {
			alert("Error al buscar enfermeras por nombre.");
		}
	});

});


function agregarGrilla(lista){
	 $('#id_table').DataTable().clear();
	 $('#id_table').DataTable().destroy();
	 $('#id_table').DataTable({
			data: lista,
			language: IDIOMA,
			searching: true,
			ordering: true,
			processing: true,
			pageLength: 10,
			lengthChange: true,
			info:true,
			scrollY: 305,
	        scroller: {
	            loadingIndicator: true
	        },
			columns:[
				{data: "idEnfermera",className:'text-center'},
				{data: "nombres",className:'text-center'},
				{data: "apellidos",className:'text-center'},
				{data: "dni", className:'text-center'},
				{data: function(row, type, val, meta){
					return row.fechaNacimiento.day + "/" + row.fechaNacimiento.month + "/" + row.fechaNacimiento.year;
				},className:'text-center'},
				{data: "especialidad", className:'text-center'},
				{data: "telefono", className:'text-center'},
				{data: "turno", className:'text-center'},
			]
	    });
}

	var IDIOMA = {
		processing:"procesando...",
	    lengthMenu: "_MENU_ Registros por p&aacute;gina",
	    zeroRecords: "No existen registros",
	    info: "P&aacute;gina _PAGE_ de _PAGES_",
	    infoEmpty: "Sin registros",
	    infoFiltered: "(Filtro de _MAX_ registros)",
	    search: "Buscar:",
	    paginate: {
	        "first":      "Primero",
	        "last":       "Last",
	        "next":       "Siguiente",
	        "previous":   "Anterior"
	    }
	};

</script>

</body>
</html>