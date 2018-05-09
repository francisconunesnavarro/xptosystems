# xptosystems

# Base de Dados

Utilizado o banco de dados MySQL e criado a base de dados xptosystems.

# Configuração

Foi configurado para o localhost utilizar a porta 9080.

# Comandos disponíveis

Comando com o exemplo entre parenteses

Estado:

-- http://localhost:9080/estados/

-- http://localhost:9080/estados/{id} ( http://localhost:9080/estados/10 )

-- http://localhost:9080/estados/{id}/cidades ( http://localhost:9080/estados/20/cidades )

-- http://localhost:9080/estados/buscauf?uf={uf} ( http://localhost:9080/estados/buscauf?uf=SP )

Cidade:

-- http://localhost:9080/cidades

-- http://localhost:9080/cidades/all

-- http://localhost:9080/cidades/{id} ( http://localhost:9080/cidades/250 )

-- http://localhost:9080/cidades/capitais

-- http://localhost:9080/cidades/qtdcidades

-- http://localhost:9080/cidades/qtdregistros

-- http://localhost:9080/cidades/qtdregistros/ibge

-- http://localhost:9080/cidades/qtdregistros/nome

-- http://localhost:9080/cidades/qtdregistros/uf

-- http://localhost:9080/cidades/qtdregistros/microregiao

-- http://localhost:9080/cidades/qtdregistros/mesoregiao

-- http://localhost:9080/cidades/busca

-- http://localhost:9080/cidades/busca?name={name} ( http://localhost:9080/cidades/busca?name=Preto )

-- http://localhost:9080/cidades/busca?uf={uf} ( http://localhost:9080/cidades/busca?uf=P )

-- http://localhost:9080/cidades/busca?ibge={ibgeId} ( http://localhost:9080/cidades/busca?ibge=2 )

-- http://localhost:9080/cidades/busca?noAccents={noAccents} ( http://localhost:9080/cidades/busca?noAccents=e )

-- http://localhost:9080/cidades/busca?alterNames={alterNames} ( http://localhost:9080/cidades/busca?alterNames=e )

-- http://localhost:9080/cidades/busca?microregion={microregion} ( http://localhost:9080/cidades/busca?microregion=w )

-- http://localhost:9080/cidades/busca?mesoregion={mesoregion} ( http://localhost:9080/cidades/busca?mesoregion=q )
