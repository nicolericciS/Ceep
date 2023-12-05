package br.com.alura.ceep.ui.webclient

import android.util.Log
import br.com.alura.ceep.model.Nota
import br.com.alura.ceep.ui.webclient.model.NotaRequisicao
import br.com.alura.ceep.ui.webclient.services.NotaService

private const val TAG = "NotaWebClient"

class NotaWebClient {

    private val notaService: NotaService = RetrofitInicializador().notaService

    suspend fun buscaTodas(): List<Nota>? {
        return try {
            val notasResposta = notaService
                .buscaTodas()
            notasResposta.map { notaResposta ->
                notaResposta.nota
            }
        } catch (e: Exception) {
            null
        }
    }

    suspend fun salva(nota: Nota): Boolean {
        try {
            val resposta = notaService.salva(
                nota.id, NotaRequisicao(
                    titulo = nota.titulo,
                    descricao = nota.descricao,
                    imagem = nota.imagem
                )
            )
            return resposta.isSuccessful

            if (resposta.isSuccessful) {
                Log.i(TAG, "Salva: Nota foi salva com sucesso")
            } else {
                Log.i(TAG, "Salva: Nota não foi salva")
            }

        } catch (e: Exception) {
            Log.e(TAG, "SALVA: Falha ao tentar salvar ", e)
        }
        return false
    }

    suspend fun remove(id: String): Boolean {
        try {
            val resposta = notaService.remove(id)
            return resposta.isSuccessful
        } catch (e: Exception) {
            Log.e(TAG, "REMOVE: Falha ao tentar remover ", e)
        }
        return false
    }
}