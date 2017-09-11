package com.dynamicheart.raven.ui.main.outbox

import com.dynamicheart.raven.injection.ConfigPersistent
import javax.inject.Inject

@ConfigPersistent
class OutboxPresenter
@Inject constructor(): OutboxContract.Presenter() {
}