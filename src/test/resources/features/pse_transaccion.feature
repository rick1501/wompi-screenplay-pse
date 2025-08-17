# language: es
@pse
Característica: Creación de transacción PSE en Wompi
  Como QA
  Quiero crear una transacción PSE en sandbox
  Para validar un flujo exitoso y escenarios alternos

  Antecedentes:
    Dado que configuro el API de Wompi

  Escenario: Creación exitosa de transacción PSE (respuesta inicial PENDING/CREATED)
    Cuando obtengo el token de aceptación del merchant
    Y creo una transacción PSE válida
    Entonces el código de respuesta debe ser 201
    Y el estado inicial de la transacción debe ser válido

  Escenario: Error al crear PSE por documento inválido
    Cuando obtengo el token de aceptación del merchant
    Y intento crear una transacción PSE con documento "000"
    Entonces el código de respuesta debe ser 422
