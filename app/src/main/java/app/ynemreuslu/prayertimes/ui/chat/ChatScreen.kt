package app.ynemreuslu.prayertimes.ui.chat


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.ynemreuslu.prayertimes.R
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch


@Composable
fun ChatScreen(
    uiState: ChatContract.UiState,
    onAction: (ChatContract.UiAction) -> Unit,
    uiEffect: Flow<ChatContract.UiEffect>,
) {


    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        bottomBar = {
            MessageInput(
                onSendMessage = { inputText ->
                    onAction(
                        ChatContract.UiAction.SendMessage(
                            ChatMessage(text = inputText)
                        )
                    )
                },
                resetScroll = {
                    coroutineScope.launch {
                        listState.scrollToItem(0)
                    }
                },
                uiState = uiState,
                onAction = onAction
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            ChatList(uiState.chatMessageState.messages, listState)
        }
    }
}

@Composable
fun ChatList(
    chatMessages: List<ChatMessage>,
    listState: LazyListState
) {
    LazyColumn(
        reverseLayout = true,
        state = listState
    ) {
        items(chatMessages.reversed()) { message ->
            ChatBubbleItem(message)
        }
    }
}

@Composable
fun ChatBubbleItem(
    chatMessage: ChatMessage
) {
    val isModelMessage = chatMessage.participant == Participant.MODEL ||
            chatMessage.participant == Participant.ERROR

    val backgroundColor = when (chatMessage.participant) {
        Participant.MODEL -> MaterialTheme.colorScheme.primaryContainer
        Participant.USER -> MaterialTheme.colorScheme.tertiaryContainer
        Participant.ERROR -> MaterialTheme.colorScheme.errorContainer
    }

    val bubbleShape = if (isModelMessage) {
        RoundedCornerShape(4.dp, 20.dp, 20.dp, 20.dp)
    } else {
        RoundedCornerShape(20.dp, 4.dp, 20.dp, 20.dp)
    }

    val horizontalAlignment = if (isModelMessage) {
        Alignment.Start
    } else {
        Alignment.End
    }

    Column(
        horizontalAlignment = horizontalAlignment,
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = chatMessage.participant.name,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        Row {
            if (chatMessage.isPending) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(all = 8.dp)
                )
            }
            BoxWithConstraints {
                Card(
                    colors = CardDefaults.cardColors(containerColor = backgroundColor),
                    shape = bubbleShape,
                    modifier = Modifier.widthIn(0.dp, maxWidth * 0.9f)
                ) {
                    Text(
                        text = chatMessage.text,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun MessageInput(
    onSendMessage: (String) -> Unit,
    resetScroll: () -> Unit = {},
    uiState: ChatContract.UiState,
    onAction: (ChatContract.UiAction) -> Unit,
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        BasicTextField(
            value = uiState.userMessage,
            onValueChange = {
                onAction(
                    ChatContract.UiAction.EnterMessage(it)
                )
            },
            modifier = Modifier
                .weight(1f)
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.outline,
                    shape = RoundedCornerShape(24.dp)
                )
                .background(
                    color = MaterialTheme.colorScheme.surface,
                    shape = RoundedCornerShape(24.dp)
                )
                .heightIn(min = 48.dp, max = 120.dp)
                .padding(horizontal = 16.dp, vertical = 12.dp)
                .fillMaxWidth(),
            textStyle = TextStyle(
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 15.sp,
                lineHeight = 20.sp
            ),
            maxLines = if (uiState.isExpanded) 5 else 1,
            decorationBox = { innerTextField ->
                Box {
                    if (uiState.userMessage.isEmpty()) {
                        Text(
                            stringResource(R.string.chat_message),
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f),
                            fontSize = 15.sp,
                            lineHeight = 20.sp
                        )
                    }
                    innerTextField()
                }
            }
        )
        Spacer(modifier = Modifier.width(8.dp))
        IconButton(
            onClick = {
                if (uiState.userMessage.isNotBlank()) {
                    onSendMessage(uiState.userMessage)
                    resetScroll()
                }
            },
            modifier = Modifier
                .size(40.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(
                    if (uiState.userMessage.isNotBlank())
                        MaterialTheme.colorScheme.primary
                    else
                        MaterialTheme.colorScheme.surfaceVariant
                ),
            enabled = uiState.userMessage.isNotBlank()
        ) {
            Icon(
                Icons.AutoMirrored.Outlined.Send,
                contentDescription = stringResource(R.string.not_icon),
                modifier = Modifier.size(20.dp),
                tint = if (uiState.userMessage.isNotBlank())
                    MaterialTheme.colorScheme.onPrimary
                else
                    MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}
