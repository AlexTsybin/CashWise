package com.alextsy.designsystem.component.button

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.credentials.Credential
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import com.alextsy.designsystem.BuildConfig
import com.alextsy.designsystem.R
import com.alextsy.designsystem.component.preview.CwPreview
import com.alextsy.designsystem.component.preview.PreviewSurface
import com.alextsy.designsystem.component.text.CwText
import com.alextsy.designsystem.theme.LocalShapes
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import kotlinx.coroutines.launch

@Composable
fun CwButton(
    text: String,
    buttonType: ButtonType = ButtonType.FILLED_TONAL,
    icon: Int? = null,
    isFullWidth: Boolean = false,
    onClick: () -> Unit,
) {
    Button(
        onClick = onClick,
        modifier = Modifier.buttonWidth(isFullWidth),
        colors = buttonType.getButtonColor(),
        elevation = buttonType.getButtonElevation(),
        shape = LocalShapes.current.button,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            icon?.let {
                Image(
                    painter = painterResource(id = icon),
                    contentDescription = "$text button",
                    modifier = Modifier.size(24.dp),
                )
                Spacer(modifier = Modifier.width(8.dp))
            }
            CwText(
                text = text,
                textType = buttonType.getTextType(),
            )
        }
    }
}

@Composable
fun GoogleButton(
    text: String,
    onSuccess: (Credential) -> Unit,
    onFailure: (String) -> Unit,
) {
    val context = LocalContext.current
    val manager = CredentialManager.create(context)
    val scope = rememberCoroutineScope()
    val clientId = BuildConfig.WEB_CLIENT_ID
    CwButton(
        text = text,
        icon = R.drawable.google,
        isFullWidth = true,
        buttonType = ButtonType.ELEVATED,
    ) {
        val option = GetGoogleIdOption.Builder()
            .setFilterByAuthorizedAccounts(false)
            .setServerClientId(clientId)
            .build()

        val request = GetCredentialRequest.Builder()
            .addCredentialOption(option)
            .build()

        scope.launch {
            try {
                val result = manager.getCredential(context, request)
                onSuccess(result.credential)
            } catch (e: Exception) {
                onFailure(e.message.toString())
            }
        }
    }
}

@CwPreview
@Composable
fun ButtonPreviews() {
    PreviewSurface {
        CwButton(
            text = "sample",
            isFullWidth = false,
        ) { }
        CwButton(
            text = "sample",
            isFullWidth = true,
        ) { }
        CwButton(
            text = "sample",
            isFullWidth = true,
            buttonType = ButtonType.TEXT,
        ) { }
        GoogleButton(
            text = "Sign in",
            onSuccess = {},
            onFailure = {},
        )
    }
}