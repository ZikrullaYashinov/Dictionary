package zikrulla.production.dictionary.ui.model

data class BottomSheetModel(
    val icon: Int,
    val text: String,
    val click: () -> Unit,
)
