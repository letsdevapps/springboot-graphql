# Springboot GraphQL

## Docker

	docker build -t springboot-graphql .
	
	docker run --rm -p 8080:8080 springboot-graphql
	
## Endpoint GraphQL

	curl -X POST http://localhost:8080/graphql \
	-H "Content-Type: application/json" \
	-d '{"query":"{ message }"}'

## Endpoint POSTMAN

	URL: POST http://localhost:8080/graphql
	
	Headers:
		Key: Content-Type
		Value: application/json
	
	Body: Raw - JSON
	{
	  	"query": "{ message }"
	}

Resposta esperada

	{
	    "data": {
	        "message": "----- Springboot GraphQL | GraphQL Index -----"
	    }
	}