package sl.com.eightdigitz.presentation.extensions

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
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

fun ImageView.setImage(
    url: String,
    placeHolder: Int = 0,
    onLoadImage: (Drawable) -> Unit
) {
    Glide.with(this.context)
        .asDrawable()
        .load(url)
        .apply(RequestOptions.circleCropTransform())
        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
        .placeholder(placeHolder)
        .into(object : CustomTarget<Drawable>() {
            override fun onLoadCleared(placeholder: Drawable?) {
                this@setImage.setImageDrawable(placeholder)
            }

            override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
                this@setImage.setImageDrawable(resource)
                onLoadImage(resource)
            }
        })
}