Feature: Product Store

  @EXAMEN
  Scenario: Validación del Precio de un Producto
    Given estoy en la página de la tienda
    And me logueo con mi usuario "s10roberto_balejandro@yahoo.es" y clave "B**RT**10"
    When navego a la categoria 'Clothes' y subcategoria 'Men'
    And agrego 2 unidades del primer producto al carrito
    Then valido en el popup la confirmación del agregado
    And valido en el popup que el monto total sea calculado correctamente
    When finalizo la compra
    Then valido el título de la página del carrito
    And vuelvo a validar el cálculo de precios en el carrito.