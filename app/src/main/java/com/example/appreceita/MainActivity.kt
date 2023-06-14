package com.example.appreceita

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.clickable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import com.example.appreceita.ui.theme.AppReceitaTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            Column() {
                Text(
                    "Receitas Rápidas e Fáceis de Fazer!",
                    //define a cor
                    color = MaterialTheme.colorScheme.secondary,
                    //define o estilo
                    style = MaterialTheme.typography.titleSmall
                )

                AppReceitaTheme {
                    //chamada ao arquivo SampleData
                    Conversation(SampleData.conversationSample)

                }
            }
        }
    }
}



data class Message(val author: String, val body: String, val body2: String )

@Composable
fun MessageCard(msg: Message) {

    //Adicionar preenchimento em torno de nossa mensagem
    Row(modifier = Modifier.padding(all = 8.dp)) {
        Image(
            painter = painterResource(R.drawable.receita),
            contentDescription = "Contact profile picture",
            modifier = Modifier
                // Define o tamanho da imagem
                .size(50.dp)

        )



        //Adicione um espaço horizontal entre a imagem e a coluna
        Spacer(modifier = Modifier.width(8.dp))

        // Acompanhamos se a mensagem é expandida ou não neste
        // variável
        var isExpanded by remember { mutableStateOf(false) }
        // surfaceColor será atualizado gradativamente de uma cor para outra
        val surfaceColor by animateColorAsState(
            if (isExpanded) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface,
        )

        Column(modifier = Modifier.clickable { isExpanded = !isExpanded }) {
            Text(
                text = msg.author,
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.titleSmall
            )
            // Adiciona um espaço vertical entre o autor e os textos da mensagem
            Spacer(modifier = Modifier.height(4.dp))
            Surface(
                shape = MaterialTheme.shapes.medium,
                shadowElevation = 1.dp,
                // a cor da superfícieColor mudará gradualmente de primária para superfície
                color = surfaceColor,
                // animateContentSize mudará o tamanho da superfície gradualmente
                modifier = Modifier
                    .animateContentSize()
                    .padding(1.dp)
            ) {
                Text(
                    text = msg.body,
                    modifier = Modifier.padding(all = 4.dp),
                    // Se a mensagem for expandida, exibimos todo o seu conteúdo
                    // senão mostramos apenas a primeira linha
                    maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Text(
                text = msg.body2,
                modifier = Modifier.padding(all = 4.dp),
                // Se a mensagem for expandida, exibimos todo o seu conteúdo
                // senão mostramos apenas a primeira linha
                maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                style = MaterialTheme.typography.bodySmall
            )

        }
    }
}


@Preview
@Composable
fun PreviewMessageCard()
{
    AppReceitaTheme {
        Surface {
            MessageCard(
                msg = Message("Colega", "Dê uma olhada no Jetpack Compose, é ótimo!","")
            )
        }
    }
}

@Composable
fun Conversation(messages: List<Message>) {
    LazyColumn {
        items(messages) { message ->
            MessageCard(message)
        }
    }
}

@Preview
@Composable
fun PreviewConversation() {
    AppReceitaTheme() {
        Conversation(SampleData.conversationSample)
    }
}