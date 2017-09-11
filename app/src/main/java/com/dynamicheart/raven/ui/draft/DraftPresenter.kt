package com.dynamicheart.raven.ui.draft

import com.dynamicheart.raven.injection.ConfigPersistent
import javax.inject.Inject

@ConfigPersistent
class DraftPresenter
@Inject constructor() : DraftContract.Presenter() {
}