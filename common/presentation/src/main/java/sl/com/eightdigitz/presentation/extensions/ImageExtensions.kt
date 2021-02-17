package sl.com.eightdigitz.presentation.extensions

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition

fun ImageView.loadImage(url: String) = Glide.with(this).load(url).into(this)

fun ImageView.loadImageRound(url: String) =
    Glide.with(this).load(url).apply(
        RequestOptions.circleCropTransform().diskCacheStrategy(
            DiskCacheStrategy.ALL
        )
    ).into(this)

fun ImageView.loadDrawable(@DrawableRes drawable: Int) {
    Glide.with(this.context).load(drawable).into(this)
}

fun ImageView.setRoundCornerImage(
    url: String,
    placeHolder: Int = 0,
    onLoadImage: (Drawable) -> Unit
) {
    Glide.with(this.context)
        .asDrawable()
        .load(url)
        .transform(RoundedCorners(10))
        .apply(RequestOptions.circleCropTransform())
        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
        .placeholder(placeHolder)
        .into(object : CustomTarget<Drawable>() {
            override fun onLoadCleared(placeholder: Drawable?) {
                this@setRoundCornerImage.setImageDrawable(placeholder)
            }

            override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
                this@setRoundCornerImage.setImageDrawable(resource)
                onLoadImage(resource)
            }
        })
}

fun ImageView.setRoundedImage(
    url: String,
    placeHolder: Int = 0,
    radius: Int
) {
    var requestOptions = RequestOptions()
    requestOptions = requestOptions.transforms(CenterCrop(), RoundedCorners(radius))
    Glide.with(this.context)
        .asDrawable()
        .load(url)
        .apply(requestOptions)
        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
        .placeholder(placeHolder)
        .into(this)
}