<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!--
    CRUD adaptado para entity.Enfermera.
    Supone:
      - Servlet: @WebServlet("/crudEnfermeraAlias")
      - Métodos: listaPorNombre, registra, actualiza, eliminacionFisica
      - Campos: idEnfermera, nombres, apellidos, dni, fechaNacimiento,
                especialidad, telefono, turno
    No incluye combo Tipo porque entity.Enfermera no declara un atributo Tipo.
-->
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>CRUD de Enfermeras</title>

    <script src="js/bootstrap.bundle.js" type="text/javascript"></script>
    <script src="js/jquery-4.0.0.min.js" type="text/javascript"></script>
    <script src="js/datatables.js" type="text/javascript"></script>
    <script src="js/sweetalert2@11.js" type="text/javascript"></script>

    <link href="css/bootstrap.css" rel="stylesheet">
    <link href="css/datatables.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4">
    <h1>CRUD de Enfermeras</h1>

    <div class="row mt-4 align-items-end">
        <div class="col-md-3">
            <label for="nombresBuscar" class="form-label">Nombres</label>
            <input type="text" class="form-control" id="nombresBuscar" placeholder="Ingrese los nombres" maxlength="100">
        </div>
        <div class="col-md-3">
            <button class="btn btn-primary w-100" id="btnBuscar" type="button">Buscar</button>
        </div>
        <div class="col-md-3">
            <button class="btn btn-success w-100" type="button" onclick="abrirModalRegistro()">Registrar enfermera</button>
        </div>
    </div>

    <div class="row mt-4">
        <div class="col-12 table-responsive">
            <table class="table table-striped table-bordered" id="id_table">
                <thead>
                <tr>
                    <th>Código</th>
                    <th>Nombres</th>
                    <th>Apellidos</th>
                    <th>DNI</th>
                    <th>Fecha de nacimiento</th>
                    <th>Especialidad</th>
                    <th>Teléfono</th>
                    <th>Turno</th>
                    <th>Editar</th>
                    <th>Eliminar</th>
                </tr>
                </thead>
                <tbody></tbody>
            </table>
        </div>
    </div>
</div>

<!-- Modal de registro -->
<div class="modal fade" id="modalRegistro" tabindex="-1" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Registro de enfermera</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Cerrar"></button>
            </div>
            <div class="modal-body">
                <form id="formRegistro">
                    <input type="hidden" name="metodo" value="registra">
                    <div class="row g-3">
                        <div class="col-md-6">
                            <label for="reg_nombres" class="form-label">Nombres</label>
                            <input class="form-control" id="reg_nombres" name="nombres" type="text" maxlength="100" required>
                        </div>
                        <div class="col-md-6">
                            <label for="reg_apellidos" class="form-label">Apellidos</label>
                            <input class="form-control" id="reg_apellidos" name="apellidos" type="text" maxlength="100" required>
                        </div>
                        <div class="col-md-6">
                            <label for="reg_dni" class="form-label">DNI</label>
                            <input class="form-control" id="reg_dni" name="dni" type="text" maxlength="8" pattern="[0-9]{8}" title="Ingrese 8 dígitos" required>
                        </div>
                        <div class="col-md-6">
                            <label for="reg_fechaNacimiento" class="form-label">Fecha de nacimiento</label>
                            <input class="form-control" id="reg_fechaNacimiento" name="fechaNacimiento" type="date">
                        </div>
                        <div class="col-md-6">
                            <label for="reg_especialidad" class="form-label">Especialidad</label>
                            <input class="form-control" id="reg_especialidad" name="especialidad" type="text" maxlength="100" required>
                        </div>
                        <div class="col-md-6">
                            <label for="reg_telefono" class="form-label">Teléfono</label>
                            <input class="form-control" id="reg_telefono" name="telefono" type="tel" maxlength="20" required>
                        </div>
                        <div class="col-md-6">
                            <label for="reg_turno" class="form-label">Turno</label>
                            <select class="form-select" id="reg_turno" name="turno" required>
                                <option value="">[Seleccione]</option>
                                <option value="Mañana">Mañana</option>
                                <option value="Tarde">Tarde</option>
                                <option value="Noche">Noche</option>
                                <option value="Rotativo">Rotativo</option>
                            </select>
                        </div>
                    </div>
                    <div class="text-center mt-4">
                        <button type="button" id="btnRegistrar" class="btn btn-primary">Registrar</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<!-- Modal de actualización -->
<div class="modal fade" id="modalActualiza" tabindex="-1" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Actualizar enfermera</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Cerrar"></button>
            </div>
            <div class="modal-body">
                <form id="formActualiza">
                    <input type="hidden" name="metodo" value="actualiza">
                    <input type="hidden" name="idEnfermera" id="act_idEnfermera">
                    <div class="row g-3">
                        <div class="col-md-6">
                            <label for="act_nombres" class="form-label">Nombres</label>
                            <input class="form-control" id="act_nombres" name="nombres" type="text" maxlength="100" required>
                        </div>
                        <div class="col-md-6">
                            <label for="act_apellidos" class="form-label">Apellidos</label>
                            <input class="form-control" id="act_apellidos" name="apellidos" type="text" maxlength="100" required>
                        </div>
                        <div class="col-md-6">
                            <label for="act_dni" class="form-label">DNI</label>
                            <input class="form-control" id="act_dni" name="dni" type="text" maxlength="8" pattern="[0-9]{8}" title="Ingrese 8 dígitos" required>
                        </div>
                        <div class="col-md-6">
                            <label for="act_fechaNacimiento" class="form-label">Fecha de nacimiento</label>
                            <input class="form-control" id="act_fechaNacimiento" name="fechaNacimiento" type="date">
                        </div>
                        <div class="col-md-6">
                            <label for="act_especialidad" class="form-label">Especialidad</label>
                            <input class="form-control" id="act_especialidad" name="especialidad" type="text" maxlength="100" required>
                        </div>
                        <div class="col-md-6">
                            <label for="act_telefono" class="form-label">Teléfono</label>
                            <input class="form-control" id="act_telefono" name="telefono" type="tel" maxlength="20" required>
                        </div>
                        <div class="col-md-6">
                            <label for="act_turno" class="form-label">Turno</label>
                            <select class="form-select" id="act_turno" name="turno" required>
                                <option value="">[Seleccione]</option>
                                <option value="Mañana">Mañana</option>
                                <option value="Tarde">Tarde</option>
                                <option value="Noche">Noche</option>
                                <option value="Rotativo">Rotativo</option>
                            </select>
                        </div>
                    </div>
                    <div class="text-center mt-4">
                        <button type="button" id="btnActualizar" class="btn btn-primary">Actualizar</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    const URL_SERVLET = 'crudEnfermeraAlias';
    let tablaEnfermeras = null;

    const IDIOMA = {
        processing: "Procesando...",
        lengthMenu: "_MENU_ registros por página",
        zeroRecords: "No existen registros",
        info: "Página _PAGE_ de _PAGES_",
        infoEmpty: "Sin registros",
        infoFiltered: "(Filtrado de _MAX_ registros)",
        search: "Buscar:",
        paginate: {
            first: "Primero",
            last: "Último",
            next: "Siguiente",
            previous: "Anterior"
        }
    };

    $(document).ready(function () {
        buscarEnfermeras("");

        $('#btnBuscar').click(function () {
            buscarEnfermeras($('#nombresBuscar').val());
        });

        $('#nombresBuscar').on('keypress', function (e) {
            if (e.which === 13) {
                e.preventDefault();
                buscarEnfermeras($('#nombresBuscar').val());
            }
        });

        $('#btnRegistrar').click(function () {
            const form = document.getElementById('formRegistro');
            if (!form.reportValidity()) return;

            $.ajax({
                url: URL_SERVLET,
                type: 'POST',
                data: $('#formRegistro').serialize(),
                dataType: 'json',
                success: function (data) {
                    agregarGrilla(data);
                    bootstrap.Modal.getOrCreateInstance(document.getElementById('modalRegistro')).hide();
                    form.reset();
                    Swal.fire({title: 'Correcto', text: 'Enfermera registrada.', icon: 'success'});
                },
                error: function (xhr) {
                    console.error('Error al registrar:', xhr.responseText);
                    Swal.fire({title: 'Error', text: 'No se pudo registrar la enfermera.', icon: 'error'});
                }
            });
        });

        $('#btnActualizar').click(function () {
            const form = document.getElementById('formActualiza');
            if (!form.reportValidity()) return;

            $.ajax({
                url: URL_SERVLET,
                type: 'POST',
                data: $('#formActualiza').serialize(),
                dataType: 'json',
                success: function (data) {
                    agregarGrilla(data);
                    bootstrap.Modal.getOrCreateInstance(document.getElementById('modalActualiza')).hide();
                    Swal.fire({title: 'Correcto', text: 'Datos actualizados.', icon: 'success'});
                },
                error: function (xhr) {
                    console.error('Error al actualizar:', xhr.responseText);
                    Swal.fire({title: 'Error', text: 'No se pudo actualizar la enfermera.', icon: 'error'});
                }
            });
        });
    });

    function buscarEnfermeras(nombres) {
        $.ajax({
            url: URL_SERVLET,
            type: 'GET',
            data: {metodo: 'listaPorNombre', nombres: nombres},
            dataType: 'json',
            success: function (lista) {
                agregarGrilla(lista);
            },
            error: function (xhr) {
                console.error('Error al buscar enfermeras:', xhr.responseText);
                Swal.fire({title: 'Error', text: 'No se pudieron cargar las enfermeras.', icon: 'error'});
            }
        });
    }

    function agregarGrilla(lista) {
        if (tablaEnfermeras !== null) {
            tablaEnfermeras.destroy();
            $('#id_table tbody').empty();
        }

        tablaEnfermeras = $('#id_table').DataTable({
            data: lista,
            language: IDIOMA,
            searching: true,
            ordering: true,
            processing: true,
            pageLength: 10,
            lengthChange: true,
            info: true,
            scrollX: true,
            columns: [
                {data: 'idEnfermera', className: 'text-center'},
                {data: 'nombres', className: 'text-center', defaultContent: ''},
                {data: 'apellidos', className: 'text-center', defaultContent: ''},
                {data: 'dni', className: 'text-center', defaultContent: ''},
                {data: 'fechaNacimiento', className: 'text-center', defaultContent: ''},
                {data: 'especialidad', className: 'text-center', defaultContent: ''},
                {data: 'telefono', className: 'text-center', defaultContent: ''},
                {data: 'turno', className: 'text-center', defaultContent: ''},
                {
                    data: null,
                    className: 'text-center',
                    orderable: false,
                    render: function (data, type, row, meta) {
                        return '<button type="button" class="btn btn-info btn-sm" onclick="verFormularioActualiza(' + meta.row + ')">Editar</button>';
                    }
                },
                {
                    data: null,
                    className: 'text-center',
                    orderable: false,
                    render: function (data, type, row) {
                        return '<button type="button" class="btn btn-danger btn-sm" onclick="eliminacionFisica(' + Number(row.idEnfermera) + ')">Eliminar</button>';
                    }
                }
            ]
        });
    }

    function abrirModalRegistro() {
        document.getElementById('formRegistro').reset();
        bootstrap.Modal.getOrCreateInstance(document.getElementById('modalRegistro')).show();
    }

    function verFormularioActualiza(indiceGrilla) {
        const enfermera = tablaEnfermeras.row(indiceGrilla).data();

        $('#act_idEnfermera').val(enfermera.idEnfermera);
        $('#act_nombres').val(enfermera.nombres || '');
        $('#act_apellidos').val(enfermera.apellidos || '');
        $('#act_dni').val(enfermera.dni || '');
        $('#act_fechaNacimiento').val(enfermera.fechaNacimiento || '');
        $('#act_especialidad').val(enfermera.especialidad || '');
        $('#act_telefono').val(enfermera.telefono || '');
        $('#act_turno').val(enfermera.turno || '');

        bootstrap.Modal.getOrCreateInstance(document.getElementById('modalActualiza')).show();
    }

    function eliminacionFisica(id) {
        Swal.fire({
            title: '¿Está seguro?',
            text: 'La enfermera será eliminada permanentemente.',
            icon: 'warning',
            showCancelButton: true,
            confirmButtonText: 'Sí, eliminar',
            cancelButtonText: 'Cancelar'
        }).then(function (result) {
            if (!result.isConfirmed) return;

            $.ajax({
                url: URL_SERVLET,
                type: 'POST',
                data: {metodo: 'eliminacionFisica', idEnfermera: id},
                dataType: 'json',
                success: function (data) {
                    agregarGrilla(data);
                    Swal.fire({title: 'Eliminada', text: 'La enfermera fue eliminada.', icon: 'success'});
                },
                error: function (xhr) {
                    console.error('Error al eliminar:', xhr.responseText);
                    Swal.fire({title: 'Error', text: 'No se pudo eliminar la enfermera.', icon: 'error'});
                }
            });
        });
    }
</script>
</body>
</html>
