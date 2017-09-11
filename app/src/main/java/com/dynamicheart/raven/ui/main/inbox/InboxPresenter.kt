package com.dynamicheart.raven.ui.main.inbox

import com.dynamicheart.raven.injection.ConfigPersistent
import javax.inject.Inject

@ConfigPersistent
class InboxPresenter
@Inject constructor(): InboxContract.Presenter() {
}