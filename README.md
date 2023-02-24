## Linea DDA
el analizador  diferenciador digital es un algoritmo de conversion de rastreo que se basa en el calculo ya sea de dy o dx se fectua un muestreo de la linea en intervalos unitarios en una coordenada y se detreminan los valores enteros correspondientes mas proximos a la trayectoria de la linea  para la otra coordenada.

## Pendientes positivas

Para pendientes positivas ¡m es <= 1!, se hace muestreo en x en intervalos unitarios de forma que:

#### dy=1 y dx=m 

y se calcula cada valor sucesivo de y, 
#### y<sub>k</sub>+1=y<sub>k</sub>+m 
_Nota: ya que m puede ser cualquier numero real entre 0 y 1 los valores calculados de y deben redondearse al entero mas cercano._

Para pendientes positivas **m > 1**, se revierten las formulas **dx** y **dy** osea se realiza un muestreo de **y** en intervalos unitarios de forma que: dy=1, dx=1/m y se calcula cada valor sucesivo de x como: x_k+1=x_k+(1/m)

las ecuaciones se basan en la suposicion de que las lineas deben procesarse del extremo izquierdo al derecho, si este procedimiento se revierte entonces dx o dy serian -1
## Getting Started

Welcome to the VS Code Java world. Here is a guideline to help you get started to write Java code in Visual Studio Code.

## Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

Meanwhile, the compiled output files will be generated in the `bin` folder by default.

> If you want to customize the folder structure, open `.vscode/settings.json` and update the related settings there.

## Dependency Management

The `JAVA PROJECTS` view allows you to manage your dependencies. More details can be found [here](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).

   