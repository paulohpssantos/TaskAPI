# TaskAPI

API RESTful para gerenciamento de tarefas, desenvolvida em Java EE/Jakarta EE, utilizando JAX-RS, CDI e rodando no WildFly.


## Tecnologias Utilizadas
- Java 8+
- JAX-RS (REST)
- CDI (Injeção de Dependência)
- WildFly (servidor de aplicação)
- Maven (gerenciamento de dependências)

## Estrutura do Projeto
```
TaskAPI/
├── src/main/java/
│   ├── dto/                # Objetos de transferência de dados (DTOs)
│   ├── model/              # Entidades do domínio (Tarefa, Usuario)
│   ├── resource/           # Recursos REST (TarefaResource, UsuarioResource, CorsFilter, RestApplication)
│   ├── service/            # Serviços de negócio
│   ├── util/               # Utilitários e enums
│   └── META-INF/           # persistence.xml
├── src/main/webapp/
│   ├── WEB-INF/
│   │   ├── web.xml         # Configuração web
│   │   └── beans.xml       # Ativa CDI
│   └── index.jsp
├── pom.xml                 # Gerenciamento Maven
└── README.md               # Este arquivo
```

## Configuração e Deploy

1. **Pré-requisitos:**
   - Java 8 ou superior
   - Maven
   - WildFly 18+

2. **Build do projeto:**
   ```sh
   mvn clean package
   ```
   O arquivo `taskAPI.war` será gerado em `target/`.

3. **Deploy no WildFly:**
   - Copie o arquivo `taskAPI.war` para a pasta `standalone/deployments/` do WildFly.
   - Certifique-se de que o WildFly está rodando.

4. **Acesso à API:**
   - Base URL: `http://localhost:8080/taskAPI/api/`

## Endpoints Principais

- **Tarefas**
  - `POST /tarefa/listar` — Lista tarefas com filtros (enviar JSON de filtro no corpo)
  - `POST /tarefa/cadastrar` — Cadastra nova tarefa
  - `POST /tarefa/atualizar` — Atualiza tarefa existente
  - `POST /tarefa/remover` — Remove tarefa

- **Usuários**
  - `POST /usuario/cadastrar` — Cadastra novo usuário

## Exemplo de JSON de Filtro de Tarefas
```json
{
  "inicio": "2025-07-01",
  "fim": "2025-07-16",
  "userId": 1,
  "status": "ABERTA",
  "atrasadas": false
}
```

## CORS
O projeto já está configurado para aceitar requisições de qualquer origem (CORS liberado).

## Observações
- O arquivo `beans.xml` em `WEB-INF` é necessário para ativar o CDI no WildFly.
- O nome do WAR gerado é `taskAPI.war`, portanto, o contexto de acesso é `/taskAPI`.
- No WildFly adicionar a configuração para base de dados:
	jndi-name="java:jboss/datasources/PostgreSQLDS" 
    pool-name="PostgreSQLDS"

---

Desenvolvido por Paulo HP Santos.
