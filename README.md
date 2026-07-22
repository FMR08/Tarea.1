# Extracción y Normalización Automática de Fichas Técnicas

Sistema desarrollado en **Python** para procesar fichas técnicas en formato PDF, extraer su contenido y normalizar toda la información mediante un modelo de OpenAI, devolviendo un JSON estructurado.

El sistema está diseñado para funcionar de forma automática, siendo capaz de procesar PDFs digitales conservando texto, tablas y demás información relevante.

# Características

- Extracción automática de texto desde PDFs.
- Detección automática del mejor método de extracción.
- Soporte para PDFs digitales y escaneados.
- OCR mediante PP-StructureV3.
- Extracción de tablas y estructura documental.
- Normalización mediante OpenAI.
- Organización automática de resultados.
- Movimiento automático de PDFs procesados.
- Ejecución periódica del codigo.


# Arquitectura actual del proyecto

```text
PythonApplication1/
├── main.py
├── config.py
├── extractor_pdf.py
├── structure.py
├── openai_engine.py
├── ocr_engine.py/
├── utils.py/
├── test/
│   ├── proveedor_1/
│   │      ficha1.pdf
│   │      ficha2.pdf
│   │
│   └── proveedor_2/
│          ficha3.pdf
├── salida/
│   ├── resultados_proveedor_1/
│   ├── resultados_proveedor_2/
│   ├── proveedor_1/
│   └── proveedor_2/
├── logs/
├── temp/
└── env2/
```
# Funcionamiento

## 1. Búsqueda de documentos

El sistema busca automáticamente todos los archivos PDF contenidos dentro de las subcarpetas de la carpeta de entrada.

## 2. Extracción inicial

Se utiliza **PyMuPDF4LLM** para convertir el PDF en texto Markdown.

Si la cantidad de texto no supera un mínimo de caracteres, el programa utilisa PP-StructureV3 para extraer el texto del documento que detecta:

- Párrafos.
- Encabezados.
- Tablas.
- Bloques.
- Estructura del documento.

## 3. Normalización mediante OpenAI

Una vez obtenido el contenido del documento, el sistema envía simultáneamente el texto extraído localmente y el PDF original.

El modelo de OpenAI reconstruye la información utilizando un JSON, garantizando una salida consistente para todos los documentos.

Por cada documento procesado se genera un archivo JSON normalizado y el PDF es movido a la carpeta de salida.


## config.py

Centraliza toda la configuración del sistema.

Incluye:

- Carpetas.
- DPI.
- Idioma OCR.
- Parámetros de tablas.
- Opciones del ocr

---

# Dependencias

Principales librerías utilizadas para hacer el proyecto.

```text
openai
pymupdf
pymupdf4llm
paddleocr
paddlepaddle
pypdfium2
opencv-python
numpy
```

---

# Instalación

## 1. Crear un entorno virtual

```bash
python -m venv env2
```

## 2. Activar el entorno

### Windows

```bash
env2\Scripts\activate
```

## 3. Instalar las dependencias

```bash
pip install openai
pip install pymupdf
pip install pymupdf4llm
pip install paddleocr
pip install paddlepaddle
pip install pypdfium2
pip install opencv-python
pip install numpy
```

---

# Ejecución

Ejecutar el proyecto utilizando el entorno virtual:

```bash
.\env2\Scripts\python.exe main.py
```
Cada documento genera un archivo JSON con una estructura similar a la siguiente:

```json
{
  "archivo": "ejemplo.pdf",
  "carpeta_origen": "Proveedor",
  "origen_texto": "pymupdf4llm",
  "ficha": {
    "documento": {},
    "producto": {},
    "especificaciones": [],
    "tablas": [],
    "secciones": [],
    "advertencias_restricciones": [],
    "normas_certificaciones": [],
    "datos_no_determinados": []
  }
}
```

# Estrategia utilizada

El sistema utiliza dos métodos de extracción: 

1. **PyMuPDF4LLM**

   Se utiliza cuando el PDF contiene texto digital. Es un método rápido que conserva gran parte de la estructura del documento.

2. **PP-StructureV3**

   Si la cantidad de texto recuperado es insuficiente, el sistema renderiza cada página como imagen y aplica OCR mediante PP-StructureV3 para recuperar texto, tablas y estructura.


