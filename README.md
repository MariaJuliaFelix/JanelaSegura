# Janela Segura – Automação de Janelas com Sensor de Chuva

**Janela Segura** é um projeto de automação residencial desenvolvido com foco em conforto e segurança. Seu objetivo principal é abrir e fechar janelas automaticamente com base nas condições climáticas, especialmente a detecção de chuva.

## 🛠️ Tecnologias Utilizadas

- ⚙️ **Arduino UNO**
- 🌧️ **Sensor de Chuva**
- 🔄 **2 Servo Motores**
- 📱 **Aplicativo com MQTT**
- 💬 **Chatbot com Chatbase**
- 🌐 **Site desenvolvido com HTML, CSS e JavaScript**
- 🔌 **Plataforma IoT para comunicação**

## 🎯 Funcionalidades

- Abre as janelas automaticamente em clima seco.
- Fecha as janelas ao detectar chuva via sensor.
- Permite controle manual pelo aplicativo (modo manual).
- Integração com protocolo MQTT para envio/recebimento de comandos.
- Interface simples e intuitiva para o usuário.

## 📱 Aplicativo

- Desenvolvido com foco em simplicidade e rapidez de uso.
- Botões para abrir e fechar a janela remotamente.
- Feedback em tempo real da situação da janela.
- Comunicação via MQTT com o Arduino.

## 🌐 Site Oficial

O projeto conta também com um **site de divulgação** feito com HTML, CSS e JavaScript, com o objetivo de apresentar o produto ao público de forma clara e visual.  

No site, é possível encontrar:

- Explicações sobre o funcionamento do sistema
- Comentários de usuários
- Botões de download para as versões do aplicativo (App Store e Google Play)
- **Um chatbot integrado** com tecnologia Chatbase, que responde perguntas frequentes dos usuários
- Área de FAQ para dúvidas comuns

## 🔧 Como Funciona

1. O sensor de chuva detecta se está molhado.
2. Caso esteja chovendo, o sistema envia um sinal ao Arduino.
3. O Arduino aciona os servos para fechar a janela.
4. Quando seca, a janela pode ser aberta automaticamente ou manualmente.
5. O usuário também pode controlar a janela via aplicativo.

## 🚀 Como Rodar o Projeto

1. **Monte o circuito no Arduino UNO** com o sensor de chuva e os servo motores.
2. **Conecte o Arduino ao computador** via USB.
3. **Configure o MQTT** com os dados do broker (pode ser o Mosquitto ou outro).
4. **Abra o app** e conecte-se ao broker MQTT.
5. Pronto! A janela será controlada automaticamente ou pelo seu comando.
6. **Abra o site no navegador** para ver a apresentação completa e interagir com o chatbot.

## 📸 Imagens do Projeto

*Adicione aqui imagens do seu circuito, aplicativo, e do site funcionando.*

## 📚 Aprendizados

Durante esse projeto, foi possível aplicar conhecimentos de:
- Eletrônica básica (sensores e atuadores)
- Programação embarcada com Arduino
- Comunicação MQTT
- Desenvolvimento web (HTML, CSS, JavaScript)
- Integração de chatbot com Chatbase
- Criação de landing page para produto
- Integração entre hardware e software

## 👩‍💻 Autoria

Maria Julia Siqueira Felix | Vinicius Donato | Yasmin Kashimura | Rebeca Corrêa |
TCC - Trabalho de conclusão de curso técnico de informatíca

💼 [LinkedIn](https://www.linkedin.com/in/maria-julia-felix-694875332/)  
📧 mariajuliaf160@gmail.com  

---

