# Wompi Screenplay + PSE (Serenity BDD)

Proyecto para el reto usando Java, Screenplay, RestAssured y Cucumber BDD.
creado por: Ricardo Narveaez C

## Requisitos
- Java 11+
- Maven 3.8+
- (Opcional) Chrome si vas a ejecutar otros tests con WebDriver

## Cómo ejecutar
```bash
mvn clean verify
```
Genera reporte en `target/serenity/index.html`.

## Estructura
- `consulta_merchant.feature`: consulta exitosa y con error 404.
- `pse_transaccion.feature`: creación de transacción PSE (201) y escenario 422.
- `tasks/`: Tasks de Screenplay para llamadas REST.
- `questions/`: Validaciones de estado y código.

## Notas
- Ambiente sandbox Wompi: `https://api-sandbox.co.uat.wompi.dev/v1`
- Llaves de prueba incluidas en `Constantes` (solo para sandbox del reto).
