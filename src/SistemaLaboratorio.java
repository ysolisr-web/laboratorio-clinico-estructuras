import java.util.Scanner;

public class SistemaLaboratorio {

    private static Lista listaGeneral = new Lista();
    private static Cola colaPendientes = new Cola();
    private static Pila historialProcesados = new Pila();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion = 0;

        do {
            mostrarMenu();
            opcion = leerEntero("Seleccione una opción (1-10): ");
            System.out.println();

            switch (opcion) {
                case 1:
                    registrarMuestra();
                    break;
                case 2:
                    verTodasLasMuestras();
                    break;
                case 3:
                    verMuestrasPendientes();
                    break;
                case 4:
                    procesarSiguienteMuestra();
                    break;
                case 5:
                    verHistorialProcesadas();
                    break;
                case 6:
                    buscarMuestraPorCodigo();
                    break;
                case 7:
                    cancelarMuestraPendiente();
                    break;
                case 8:
                    deshacerUltimoProcesamiento();
                    break;
                case 9:
                    verCantidadElementos();
                    break;
                case 10:
                    System.out.println("Saliendo del Sistema de Gestión del Laboratorio Clínico...");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor ingrese un número entre 1 y 10.");
            }
            System.out.println();
        } while (opcion != 10);

        scanner.close();
    }

    private static void mostrarMenu() {
        System.out.println("==================================================");
        System.out.println("   SISTEMA DE GESTIÓN DE LABORATORIO CLÍNICO");
        System.out.println("==================================================");
        System.out.println("1. Registrar muestra");
        System.out.println("2. Ver todas las muestras registradas");
        System.out.println("3. Ver muestras pendientes");
        System.out.println("4. Procesar siguiente muestra");
        System.out.println("5. Ver historial de muestras procesadas");
        System.out.println("6. Buscar muestra por código");
        System.out.println("7. Cancelar muestra pendiente");
        System.out.println("8. Deshacer último procesamiento");
        System.out.println("9. Ver cantidad de elementos");
        System.out.println("10. Salir");
        System.out.println("==================================================");
    }

    // 1. Registrar muestra
    private static void registrarMuestra() {
        System.out.println("--- REGISTRAR NUEVA MUESTRA ---");
        String codigo = leerTextoNoVacio("Código de la muestra (ej. M-001): ");

        // Validar que el código no exista ya en la lista general
        MuestraLaboratorio muestraBusqueda = new MuestraLaboratorio(codigo, "", "", "");
        if (listaGeneral.contiene(muestraBusqueda)) {
            System.out.println("Error: Ya existe una muestra registrada con el código '" + codigo + "'.");
            return;
        }

        String paciente = leerTextoNoVacio("Nombre del paciente: ");
        String examen = leerTextoNoVacio("Tipo de examen (ej. Hemograma, Glucosa, Perfil Lipídico): ");

        MuestraLaboratorio nuevaMuestra = new MuestraLaboratorio(codigo, paciente, examen, "Pendiente");

        // Regla obligatoria: agregar a lista general y encolar en pendientes
        listaGeneral.agregar(nuevaMuestra);
        colaPendientes.encolar(nuevaMuestra);

        System.out.println("Muestra registrada exitosamente y enviada a la cola de pendientes.");
    }

    // 2. Ver todas las muestras registradas (Lista)
    private static void verTodasLasMuestras() {
        System.out.println("--- TODAS LAS MUESTRAS REGISTRADAS (LISTA GENERAL) ---");
        if (listaGeneral.esVacia()) {
            System.out.println("No hay muestras registradas en el sistema.");
            return;
        }
        listaGeneral.mostrarAdelante();
    }

    // 3. Ver muestras pendientes (Cola)
    private static void verMuestrasPendientes() {
        System.out.println("--- MUESTRAS PENDIENTES POR PROCESAR (COLA FIFO) ---");
        colaPendientes.mostrar();
    }

    // 4. Procesar siguiente muestra (Cola -> Pila)
    private static void procesarSiguienteMuestra() {
        System.out.println("--- PROCESAR SIGUIENTE MUESTRA ---");
        if (colaPendientes.esVacia()) {
            System.out.println("No hay muestras pendientes en la cola para procesar.");
            return;
        }

        // Regla obligatoria: desencolar y apilar
        MuestraLaboratorio procesada = (MuestraLaboratorio) colaPendientes.desencolar();
        procesada.setEstado("Procesada");
        historialProcesados.apilar(procesada);

        System.out.println("Muestra procesada con éxito:");
        System.out.println(procesada);
    }

    // 5. Ver historial de muestras procesadas (Pila)
    private static void verHistorialProcesadas() {
        System.out.println("--- HISTORIAL DE MUESTRAS PROCESADAS (PILA LIFO) ---");
        historialProcesados.mostrar();
    }

    // 6. Buscar muestra por código (Lista)
    private static void buscarMuestraPorCodigo() {
        System.out.println("--- BUSCAR MUESTRA POR CÓDIGO ---");
        if (listaGeneral.esVacia()) {
            System.out.println("El sistema no tiene muestras registradas.");
            return;
        }

        String codigo = leerTextoNoVacio("Ingrese el código de la muestra a buscar: ");
        MuestraLaboratorio criterio = new MuestraLaboratorio(codigo, "", "", "");
        MuestraLaboratorio encontrada = (MuestraLaboratorio) listaGeneral.buscarDato(criterio);

        if (encontrada != null) {
            System.out.println("Muestra encontrada:");
            System.out.println(encontrada);
        } else {
            System.out.println("No se encontró ninguna muestra con el código '" + codigo + "'.");
        }
    }

    // 7. Cancelar muestra pendiente usando cola auxiliar
    private static void cancelarMuestraPendiente() {
        System.out.println("--- CANCELAR MUESTRA PENDIENTE ---");
        if (colaPendientes.esVacia()) {
            System.out.println("No hay muestras pendientes en la cola para cancelar.");
            return;
        }

        String codigo = leerTextoNoVacio("Ingrese el código de la muestra pendiente a cancelar: ");
        Cola colaAuxiliar = new Cola();
        boolean encontrada = false;
        MuestraLaboratorio muestraCancelada = null;

        // Recorrer cola original usando cola auxiliar (regla obligatoria)
        while (!colaPendientes.esVacia()) {
            MuestraLaboratorio actual = (MuestraLaboratorio) colaPendientes.desencolar();
            if (actual.getCodigoMuestra().equalsIgnoreCase(codigo) && !encontrada) {
                encontrada = true;
                muestraCancelada = actual;
                muestraCancelada.setEstado("Cancelada");
            } else {
                colaAuxiliar.encolar(actual);
            }
        }

        // Restaurar los elementos restantes a la cola original
        while (!colaAuxiliar.esVacia()) {
            colaPendientes.encolar(colaAuxiliar.desencolar());
        }

        if (encontrada) {
            System.out.println("Muestra cancelada y retirada de la cola de pendientes:");
            System.out.println(muestraCancelada);
        } else {
            System.out.println("No se encontró ninguna muestra pendiente con el código '" + codigo + "'.");
            System.out.println("(Nota: Solo se pueden cancelar muestras que aún estén en espera).");
        }
    }

    // 8. Deshacer último procesamiento (Pila -> Cola)
    private static void deshacerUltimoProcesamiento() {
        System.out.println("--- DESHACER ÚLTIMO PROCESAMIENTO ---");
        if (historialProcesados.esVacia()) {
            System.out.println("No hay procesamientos recientes en el historial para deshacer.");
            return;
        }

        // Regla obligatoria: desapilar del historial y volver a encolar en pendientes
        MuestraLaboratorio ultima = (MuestraLaboratorio) historialProcesados.desapilar();
        ultima.setEstado("Pendiente");
        colaPendientes.encolar(ultima);

        System.out.println("Se ha deshecho el último procesamiento. La muestra volvió a la cola de pendientes:");
        System.out.println(ultima);
    }

    // 9. Ver cantidad de elementos en cada estructura
    private static void verCantidadElementos() {
        System.out.println("--- REPORTE DE CANTIDAD DE ELEMENTOS ---");
        System.out.println("Total de muestras registradas (Lista General): " + listaGeneral.cuentaElementos());
        System.out.println("Muestras pendientes por procesar (Cola):     " + colaPendientes.tamanio());
        System.out.println("Muestras procesadas en historial (Pila):     " + historialProcesados.tamanio());
    }

    // Métodos auxiliares de validación de entrada
    private static int leerEntero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String entrada = scanner.nextLine().trim();
            try {
                return Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor digite un número entero.");
            }
        }
    }

    private static String leerTextoNoVacio(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String texto = scanner.nextLine().trim();
            if (!texto.isEmpty()) {
                return texto;
            }
            System.out.println("Este campo no puede quedar vacío. Intente nuevamente.");
        }
    }
}
