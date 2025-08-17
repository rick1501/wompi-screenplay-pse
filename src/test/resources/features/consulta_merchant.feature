# language: es
@merchant
Característica: Consulta de merchant en Wompi
  Como QA
  Quiero consultar información del merchant
  Para validar que responde correctamente y manejar errores

  Escenario: Consulta exitosa de merchant
    Dado que configuro el API de Wompi
    Cuando consulto el merchant con la llave pública válida
    Entonces el código de respuesta debe ser 200

  Escenario: Consulta de merchant con llave inválida
    Dado que configuro el API de Wompi
    Cuando consulto el merchant con la llave pública "pub_invalida_123456"
    Entonces el código de respuesta debe ser 404
