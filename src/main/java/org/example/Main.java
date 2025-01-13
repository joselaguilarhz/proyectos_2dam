package org.example;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        EmpresaDAO empresaDAO = new EmpresaDAO();
        DepartamentoDAO departamentoDAO = new DepartamentoDAO();
        EmpleadoDAO empleadoDAO = new EmpleadoDAO();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            mostrarMenuPrincipal();
            int opcion = scanner.nextInt();
            scanner.nextLine();  // Consumir el carácter de nueva línea

            switch (opcion) {
                case 1:
                    insertarRegistro(scanner, empresaDAO, departamentoDAO, empleadoDAO);
                    break;
                case 2:
                    leerRegistro(scanner, empresaDAO, departamentoDAO, empleadoDAO);
                    break;
                case 3:
                    actualizarRegistro(scanner, empresaDAO, departamentoDAO, empleadoDAO);
                    break;
                case 4:
                    eliminarRegistro(scanner, empresaDAO, departamentoDAO, empleadoDAO);
                    break;
                case 5:
                    consultarEmpleadosPorDepartamento(scanner, empleadoDAO);
                    break;
                case 6:
                    mostrarDepartamentosDeEmpresa(scanner, departamentoDAO);
                    break;
                case 7:
                    insertarValoresEjemplo(empresaDAO, departamentoDAO, empleadoDAO);
                    break;
                case 8:
                    limpiarTablas(empresaDAO, departamentoDAO, empleadoDAO);
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    private static void mostrarMenuPrincipal() {
        System.out.println("Seleccione una opción:");
        System.out.println("1. Insertar");
        System.out.println("2. Leer un registro por su identificador");
        System.out.println("3. Actualizar un registro existente");
        System.out.println("4. Eliminar un registro existente");
        System.out.println("5. Consultar todos los empleados de un departamento");
        System.out.println("6. Mostrar los departamentos de una empresa y el número de empleados de cada uno");
        System.out.println("7. Insertar valores de ejemplo");
        System.out.println("8. Limpiar todas las tablas");
        System.out.println("0. Salir");
    }

    private static void mostrarSubMenu() {
        System.out.println("Seleccione una entidad:");
        System.out.println("1. Empresa");
        System.out.println("2. Departamento");
        System.out.println("3. Empleado");
    }

    private static void insertarRegistro(Scanner scanner, EmpresaDAO empresaDAO, DepartamentoDAO departamentoDAO, EmpleadoDAO empleadoDAO) {
        mostrarSubMenu();
        int entidad = scanner.nextInt();
        scanner.nextLine();  // Consumir el carácter de nueva línea

        switch (entidad) {
            case 1:
                // Insertar empresa
                System.out.print("Ingrese el nombre de la empresa: ");
                String nombreEmpresa = scanner.nextLine();
                System.out.print("Ingrese la industria de la empresa: ");
                String industria = scanner.nextLine();
                Empresa empresa = new Empresa();
                empresa.setNombre(nombreEmpresa);
                empresa.setIndustria(industria);
                empresaDAO.crearEmpresa(empresa);
                System.out.println("Empresa creada con éxito.");
                break;
            case 2:
                // Insertar departamento
                System.out.print("Ingrese el nombre del departamento: ");
                String nombreDepartamento = scanner.nextLine();
                System.out.print("Ingrese el ID de la empresa asociada: ");
                Long empresaId = scanner.nextLong();
                scanner.nextLine();  // Consumir el carácter de nueva línea
                Empresa empresaAsociada = empresaDAO.leerEmpresa(empresaId);
                if (empresaAsociada != null) {
                    Departamento departamento = new Departamento();
                    departamento.setNombre(nombreDepartamento);
                    departamento.setEmpresa(empresaAsociada);
                    departamentoDAO.crearDepartamento(departamento);
                    System.out.println("Departamento creado con éxito.");
                } else {
                    System.out.println("ID de empresa no encontrado.");
                }
                break;
            case 3:
                // Insertar empleado
                System.out.print("Ingrese el nombre del empleado: ");
                String nombreEmpleado = scanner.nextLine();
                System.out.print("Ingrese el apellido del empleado: ");
                String apellido = scanner.nextLine();
                System.out.print("Ingrese el puesto del empleado: ");
                String puesto = scanner.nextLine();
                System.out.print("Ingrese el ID del departamento asociado: ");
                Long departamentoId = scanner.nextLong();
                scanner.nextLine();  // Consumir el carácter de nueva línea
                Departamento departamentoAsociado = departamentoDAO.leerDepartamento(departamentoId);
                if (departamentoAsociado != null) {
                    Empleado empleado = new Empleado();
                    empleado.setNombre(nombreEmpleado);
                    empleado.setApellido(apellido);
                    empleado.setPuesto(puesto);
                    empleado.setDepartamento(departamentoAsociado);
                    empleadoDAO.crearEmpleado(empleado);
                    System.out.println("Empleado creado con éxito.");
                } else {
                    System.out.println("ID de departamento no encontrado.");
                }
                break;
            default:
                System.out.println("Opción no válida.");
        }
    }

    private static void leerRegistro(Scanner scanner, EmpresaDAO empresaDAO, DepartamentoDAO departamentoDAO, EmpleadoDAO empleadoDAO) {
        mostrarSubMenu();
        int entidad = scanner.nextInt();
        scanner.nextLine();  // Consumir el carácter de nueva línea

        System.out.print("Ingrese el ID del registro: ");
        Long id = scanner.nextLong();
        scanner.nextLine();  // Consumir el carácter de nueva línea

        switch (entidad) {
            case 1:
                // Leer empresa
                Empresa empresa = empresaDAO.leerEmpresa(id);
                if (empresa != null) {
                    System.out.println("Empresa: " + empresa.getNombre() + ", Industria: " + empresa.getIndustria());
                } else {
                    System.out.println("Empresa no encontrada.");
                }
                break;
            case 2:
                // Leer departamento
                Departamento departamento = departamentoDAO.leerDepartamento(id);
                if (departamento != null) {
                    System.out.println("Departamento: " + departamento.getNombre() + ", Empresa: " + departamento.getEmpresa().getNombre());
                } else {
                    System.out.println("Departamento no encontrado.");
                }
                break;
            case 3:
                // Leer empleado
                Empleado empleado = empleadoDAO.leerEmpleado(id);
                if (empleado != null) {
                    System.out.println("Empleado: " + empleado.getNombre() + " " + empleado.getApellido() + ", Puesto: " + empleado.getPuesto() + ", Departamento: " + empleado.getDepartamento().getNombre());
                } else {
                    System.out.println("Empleado no encontrado.");
                }
                break;
            default:
                System.out.println("Opción no válida.");
        }
    }

    private static void actualizarRegistro(Scanner scanner, EmpresaDAO empresaDAO, DepartamentoDAO departamentoDAO, EmpleadoDAO empleadoDAO) {
        mostrarSubMenu();
        int entidad = scanner.nextInt();
        scanner.nextLine();  // Consumir el carácter de nueva línea

        System.out.print("Ingrese el ID del registro a actualizar: ");
        Long id = scanner.nextLong();
        scanner.nextLine();  // Consumir el carácter de nueva línea

        switch (entidad) {
            case 1:
                // Actualizar empresa
                Empresa empresa = empresaDAO.leerEmpresa(id);
                if (empresa != null) {
                    System.out.print("Ingrese el nuevo nombre de la empresa (actual: " + empresa.getNombre() + "): ");
                    String nuevoNombre = scanner.nextLine();
                    System.out.print("Ingrese la nueva industria de la empresa (actual: " + empresa.getIndustria() + "): ");
                    String nuevaIndustria = scanner.nextLine();
                    empresa.setNombre(nuevoNombre);
                    empresa.setIndustria(nuevaIndustria);
                    empresaDAO.actualizarEmpresa(empresa);
                    System.out.println("Empresa actualizada con éxito.");
                } else {
                    System.out.println("Empresa no encontrada.");
                }
                break;
            case 2:
                // Actualizar departamento
                Departamento departamento = departamentoDAO.leerDepartamento(id);
                if (departamento != null) {
                    System.out.print("Ingrese el nuevo nombre del departamento (actual: " + departamento.getNombre() + "): ");
                    String nuevoNombre = scanner.nextLine();
                    departamento.setNombre(nuevoNombre);
                    departamentoDAO.actualizarDepartamento(departamento);
                    System.out.println("Departamento actualizado con éxito.");
                } else {
                    System.out.println("Departamento no encontrado.");
                }
                break;
            case 3:
                // Actualizar empleado
                Empleado empleado = empleadoDAO.leerEmpleado(id);
                if (empleado != null) {
                    System.out.print("Ingrese el nuevo nombre del empleado (actual: " + empleado.getNombre() + "): ");
                    String nuevoNombre = scanner.nextLine();
                    System.out.print("Ingrese el nuevo apellido del empleado (actual: " + empleado.getApellido() + "): ");
                    String nuevoApellido = scanner.nextLine();
                    System.out.print("Ingrese el nuevo puesto del empleado (actual: " + empleado.getPuesto() + "): ");
                    String nuevoPuesto = scanner.nextLine();
                    empleado.setNombre(nuevoNombre);
                    empleado.setApellido(nuevoApellido);
                    empleado.setPuesto(nuevoPuesto);
                    empleadoDAO.actualizarEmpleado(empleado);
                    System.out.println("Empleado actualizado con éxito.");
                } else {
                    System.out.println("Empleado no encontrado.");
                }
                break;
            default:
                System.out.println("Opción no válida.");
        }
    }

    private static void eliminarRegistro(Scanner scanner, EmpresaDAO empresaDAO, DepartamentoDAO departamentoDAO, EmpleadoDAO empleadoDAO) {
        mostrarSubMenu();
        int entidad = scanner.nextInt();
        scanner.nextLine();  // Consumir el carácter de nueva línea

        System.out.print("Ingrese el ID del registro a eliminar: ");
        Long id = scanner.nextLong();
        scanner.nextLine();  // Consumir el carácter de nueva línea

        switch (entidad) {
            case 1:
                // Eliminar empresa
                empresaDAO.eliminarEmpresa(id);
                System.out.println("Empresa eliminada con éxito.");
                break;
            case 2:
                // Eliminar departamento
                departamentoDAO.eliminarDepartamento(id);
                System.out.println("Departamento eliminado con éxito.");
                break;
            case 3:
                // Eliminar empleado
                empleadoDAO.eliminarEmpleado(id);
                System.out.println("Empleado eliminado con éxito.");
                break;
            default:
                System.out.println("Opción no válida.");
        }
    }

    private static void consultarEmpleadosPorDepartamento(Scanner scanner, EmpleadoDAO empleadoDAO) {
        System.out.print("Ingrese el ID del departamento: ");
        Long departamentoId = scanner.nextLong();
        scanner.nextLine();  // Consumir el carácter de nueva línea

        List<Empleado> empleados = empleadoDAO.leerEmpleadosPorDepartamento(departamentoId);
        if (!empleados.isEmpty()) {
            System.out.println("Empleados del departamento:");
            for (Empleado empleado : empleados) {
                System.out.println("Empleado: " + empleado.getNombre() + " " + empleado.getApellido() + ", Puesto: " + empleado.getPuesto());
            }
        } else {
            System.out.println("No se encontraron empleados para el departamento especificado.");
        }
    }

    private static void mostrarDepartamentosDeEmpresa(Scanner scanner, DepartamentoDAO departamentoDAO) {
        System.out.print("Ingrese el ID de la empresa: ");
        Long empresaId = scanner.nextLong();
        scanner.nextLine();  // Consumir el carácter de nueva línea

        List<Object[]> resultados = departamentoDAO.leerDepartamentosConConteoEmpleados(empresaId);
        if (!resultados.isEmpty()) {
            System.out.println("Departamentos de la empresa y número de empleados:");
            for (Object[] resultado : resultados) {
                Long departamentoId = (Long) resultado[0];
                String nombreDepartamento = (String) resultado[1];
                Long numeroEmpleados = (Long) resultado[2];
                System.out.println("ID: " + departamentoId + ", Departamento: " + nombreDepartamento + ", Número de empleados: " + numeroEmpleados);
            }
        } else {
            System.out.println("No se encontraron departamentos para la empresa especificada.");
        }
    }

    private static void insertarValoresEjemplo(EmpresaDAO empresaDAO, DepartamentoDAO departamentoDAO, EmpleadoDAO empleadoDAO) {
        // Crear dos nuevas empresas
        Empresa empresa1 = new Empresa();
        empresa1.setNombre("TechCorp");
        empresa1.setIndustria("Tecnología");
        empresaDAO.crearEmpresa(empresa1);

        Empresa empresa2 = new Empresa();
        empresa2.setNombre("EduTech");
        empresa2.setIndustria("Educación");
        empresaDAO.crearEmpresa(empresa2);

        // Crear tres nuevos departamentos
        Departamento departamento1 = new Departamento();
        departamento1.setNombre("Desarrollo");
        departamento1.setEmpresa(empresa1);
        departamentoDAO.crearDepartamento(departamento1);

        Departamento departamento2 = new Departamento();
        departamento2.setNombre("Marketing");
        departamento2.setEmpresa(empresa1);
        departamentoDAO.crearDepartamento(departamento2);

        Departamento departamento3 = new Departamento();
        departamento3.setNombre("Recursos Humanos");
        departamento3.setEmpresa(empresa2);
        departamentoDAO.crearDepartamento(departamento3);

        // Crear cinco nuevos empleados
        Empleado empleado1 = new Empleado();
        empleado1.setNombre("Juan");
        empleado1.setApellido("Pérez");
        empleado1.setPuesto("Desarrollador");
        empleado1.setDepartamento(departamento1);
        empleadoDAO.crearEmpleado(empleado1);

        Empleado empleado2 = new Empleado();
        empleado2.setNombre("Ana");
        empleado2.setApellido("García");
        empleado2.setPuesto("Marketing");
        empleado2.setDepartamento(departamento2);
        empleadoDAO.crearEmpleado(empleado2);

        Empleado empleado3 = new Empleado();
        empleado3.setNombre("Luis");
        empleado3.setApellido("Martínez");
        empleado3.setPuesto("HR Manager");
        empleado3.setDepartamento(departamento3);
        empleadoDAO.crearEmpleado(empleado3);

        Empleado empleado4 = new Empleado();
        empleado4.setNombre("María");
        empleado4.setApellido("López");
        empleado4.setPuesto("Desarrollador");
        empleado4.setDepartamento(departamento1);
        empleadoDAO.crearEmpleado(empleado4);

        Empleado empleado5 = new Empleado();
        empleado5.setNombre("Carlos");
        empleado5.setApellido("Fernández");
        empleado5.setPuesto("Marketing");
        empleado5.setDepartamento(departamento2);
        empleadoDAO.crearEmpleado(empleado5);

        System.out.println("Valores de ejemplo insertados con éxito.");
    }

    private static void limpiarTablas(EmpresaDAO empresaDAO, DepartamentoDAO departamentoDAO, EmpleadoDAO empleadoDAO) {
        // Eliminar todos los registros de las tablas en el orden correcto
        empleadoDAO.eliminarTodosLosEmpleados();
        departamentoDAO.eliminarTodosLosDepartamentos();
        empresaDAO.eliminarTodasLasEmpresas();

        // Reiniciar AUTO_INCREMENT para cada tabla
        empleadoDAO.reiniciarAutoIncrement("Empleado");
        departamentoDAO.reiniciarAutoIncrement("Departamento");
        empresaDAO.reiniciarAutoIncrement("Empresa");

        System.out.println("Todas las tablas han sido limpiadas y AUTO_INCREMENT reiniciado.");
    }
}