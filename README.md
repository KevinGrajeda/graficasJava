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

    public void lineaPrimera(int x1, int y1, int x2, int y2) {
        float pendiente = (float)(y2 - y1) / (x2 - x1);
        float y=y1;
        for (int i = x1; i <= x2; i++) {
        y+=pendiente;
        putPixel(i, Math.round(y),Color.red);
    }
    void lineaPuntoMedio(int x1, int y1, int x2, int y2) {

        int dx = x2 - x1;
        int dy = y2 - y1;

        int f = dy - (dx / 2);
        int x = x1;
        int y = y1;
        pixel(x1, y1);
        while (x <= x2) {
            if (f > 0) {
                y++;
                f -= dx;
            }
            x++;
            f += dy;
            pixel(x, y);
        }

    }

    void lineaBresenham(int x1, int y1, int x2, int y2) {
        int deltaX = Math.abs(x2 - x1);
        int deltaY = Math.abs(y2 - y1);
        int sumaX = x1 < x2 ? 1 : -1;
        int sumaY = y1 < y2 ? 1 : -1;
        int error = deltaX - deltaY;

        int x=x1;
        int y=y1;
        while(x != x2 || y != y2) {
            pixel(x,y);
            int error2 = error * 2;
            if(error2 > -deltaY) {
                error -= deltaY;
                x += sumaX;
            }
            if(error2 < deltaX) {
                error += deltaX;
                y += sumaY;
            }
        }

    }