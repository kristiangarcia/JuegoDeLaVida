# Juego de la Vida de Conway

## Descripción

Implementación del Juego de la Vida de Conway creado por el matemático John Horton Conway en 1970. Este juego celular demuestra cómo un pequeño conjunto de reglas sencillas puede generar patrones de vida complejos.

## Características

### Mundo Esférico (Toroidal)
El juego se desarrolla sobre una matriz de 6x6 que representa un mundo esférico, es decir, las posiciones de los extremos son vecinas. Todas las células tienen exactamente 8 vecinos.

### Reglas del Juego

- **Nacimiento**: Si una posición vacía está rodeada por exactamente 3 células vecinas, se crea vida
- **Muerte por aislamiento**: Si una célula tiene menos de 2 células vecinas, muere
- **Muerte por sofocación**: Si una célula tiene más de 4 células vecinas, muere
- **Supervivencia**: Una célula con 2, 3 o 4 vecinas sobrevive a la siguiente generación

### Características Adicionales

- **Sistema de edad**: Las células tienen edad que aumenta en cada generación que sobreviven
- **Eventos aleatorios**: 10% de probabilidad de generación espontánea o muerte aleatoria
- **Visualización**: Los números indican la edad de la célula (1-9), * indica edad ≥ 10

## Estructura del Proyecto

### Celula.java
Clase que representa cada célula individual:
- Atributos: edad y estado viva/muerta
- Métodos: `nacer()`, `morir()`, `envejecer()`
- Visualización personalizada según la edad

### JuegoDeLaVida.java
Clase principal que gestiona el tablero y las reglas:
- Implementación de mundo esférico con wrap-around
- Método `contarVecinosVivos()` que calcula vecinos en topología toroidal
- Método `evolucionar()` que aplica todas las reglas del juego
- Métodos de inicialización: manual o aleatoria
- Visualización del tablero en consola

### Main.java
Programa principal que ejecuta la simulación:
- Crea un tablero de 6x6 (configurable)
- Inicializa con un patrón aleatorio del 30%
- Simula 20 generaciones con 1 segundo entre cada una
- Muestra estadísticas del número de células vivas

## Cómo Usar

### Compilar el proyecto
```bash
cd src
javac *.java
```

### Ejecutar el juego
```bash
java Main
```

### Personalización

#### Cambiar el tamaño del tablero
En `Main.java` línea 14:
```java
JuegoDeLaVida juego = new JuegoDeLaVida(6, 6); // cambiar dimensiones
```

#### Crear patrones específicos
Descomentar y modificar las líneas 20-24 en `Main.java`:
```java
juego.setCelulaViva(1, 2);
juego.setCelulaViva(2, 3);
juego.setCelulaVida(3, 1);
// etc...
```

#### Ajustar densidad inicial
En `Main.java` línea 17:
```java
juego.inicializarAleatorio(0.3); // cambiar densidad (0.0 a 1.0)
```

#### Modificar número de generaciones
En `Main.java` línea 31:
```java
for (int i = 0; i < 20; i++) { // cambiar número de generaciones
```

## Ejemplo de Salida

```
=== JUEGO DE LA VIDA DE CONWAY ===

Reglas del juego:
- Nacimiento: posición vacía con 3 vecinos → nace célula
- Muerte por aislamiento: célula con < 2 vecinos → muere
- Muerte por sofocación: célula con > 4 vecinos → muere
- Supervivencia: célula con 2, 3 o 4 vecinos → sobrevive
- Eventos aleatorios: 10% de probabilidad de generación espontánea o muerte

==============
Generación: 0
==============
║1 · 1 · · · ║
║· · · · · · ║
║· · · · 1 · ║
║1 1 · 1 · 1 ║
║· · · · · · ║
║· 1 · · 1 · ║
==============
Células vivas: 8
```

## Observaciones

A través de estas reglas elementales, el juego genera patrones verdaderamente complejos:
- **Patrones estáticos**: Estructuras que permanecen inmóviles
- **Osciladores**: Patrones que se repiten cíclicamente
- **Naves espaciales**: Patrones que parecen moverse por el tablero
- **Interacciones**: Los patrones pueden interactuar entre ellos creando comportamientos emergentes

## Autor

Proyecto educativo basado en el Juego de la Vida de John Horton Conway (1970)
