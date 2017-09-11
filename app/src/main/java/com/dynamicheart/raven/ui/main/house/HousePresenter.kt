package com.dynamicheart.raven.ui.main.house

import com.dynamicheart.raven.injection.ConfigPersistent
import javax.inject.Inject

@ConfigPersistent
class HousePresenter
@Inject constructor(): HouseContract.Presenter() {
}