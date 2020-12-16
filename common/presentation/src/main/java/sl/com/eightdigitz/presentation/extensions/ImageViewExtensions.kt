package sl.com.eightdigitz.presentation.extensions

import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

fun ImageView.loadImage(url: String?) {
    Glide.with(this.context)
        .load(url)
        .into(this)
}

fun ImageView.loadImageRound(
    url: String,
    placeholder: Int = 0 // Todo add place holder
) =
    Glide.with(this).load(url).apply(
        RequestOptions.circleCropTransform().placeholder(placeholder)
    ).into(this)

fun ImageView.loadImageCenterCrop(url: String?) {
    Glide.with(this.context)
        .load(url)
        .apply(RequestOptions().centerCrop())
        .into(this)
}

fun ImageView.loadImage(url: String?, @DrawableRes placeholder: Int) {
    Glide.with(this.context)
        .load(url)
        .apply(RequestOptions().placeholder(placeholder).centerCrop())
        .into(this)
}

/*fun ImageView.loadImageCornerRound(
    url: String?,
    radius: Float,
    placeHolder: Int = 0
) {
    Glide.with(this.context)
        .asBitmap()
        .load(url)
        .apply(RequestOptions.placeholderOf(R.drawable.ic_logo).error(R.drawable.ic_logo))
        .into(object : CustomTarget<Bitmap>() {
            override fun onLoadCleared(placeholder: Drawable?) {
                this@loadImageCornerRound.setImageDrawable(placeholder)
            }

            override fun onLoadFailed(errorDrawable: Drawable?) {
                super.onLoadFailed(errorDrawable)
                val resource = errorDrawable?.toBitmap()!!
                val bitmat = if (resource.width >= resource.height) {
                    Bitmap.createBitmap(
                        resource,
                        resource.width / 2 - resource.height / 2,
                        0,
                        resource.height,
                        resource.height
                    )
                } else {
                    Bitmap.createBitmap(
                        resource,
                        0,
                        resource.height / 2 - resource.width / 2,
                        resource.width,
                        resource.width
                    )
                }
                val roundedDrawable =
                    RoundedBitmapDrawableFactory.create(this@loadImageCornerRound.resources, bitmat)
                val raduisInt = radius.div(this@loadImageCornerRound.layoutParams.width)
                    .times(roundedDrawable.intrinsicWidth)
                roundedDrawable.cornerRadius = raduisInt
                this@loadImageCornerRound.setImageDrawable(roundedDrawable)
            }

            override fun onResourceReady(
                resource: Bitmap,
                transition: Transition<in Bitmap>?
            ) {
                val bitmat = if (resource.width >= resource.height) {
                    Bitmap.createBitmap(
                        resource,
                        resource.width / 2 - resource.height / 2,
                        0,
                        resource.height,
                        resource.height
                    )
                } else {
                    Bitmap.createBitmap(
                        resource,
                        0,
                        resource.height / 2 - resource.width / 2,
                        resource.width,
                        resource.width
                    )
                }
                val roundedDrawable =
                    RoundedBitmapDrawableFactory.create(this@loadImageCornerRound.resources, bitmat)
                val raduisInt = radius.div(this@loadImageCornerRound.layoutParams.width)
                    .times(roundedDrawable.intrinsicWidth)
                roundedDrawable.cornerRadius = raduisInt
                this@loadImageCornerRound.setImageDrawable(roundedDrawable)
            }
        })
}*/

/*internal fun ImageView.loadDrawableCornerRound(drawable: Int?, radius: Float) {
  Glide.with(this.context)
    .asBitmap()
    .load(drawable)
    *//*.apply(RequestOptions.placeholderOf(R.drawable.ic_placeholder_size_l))*//*
    .into(object : CustomTarget<Bitmap>() {
      override fun onLoadCleared(placeholder: Drawable?) {

      }

      override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
        val bitmat = if (resource.width >= resource.height) {
          Bitmap.createBitmap(
            resource,
            resource.width / 2 - resource.height / 2,
            0,
            resource.height,
            resource.height
          )
        } else {
          Bitmap.createBitmap(
            resource,
            0,
            resource.height / 2 - resource.width / 2,
            resource.width,
            resource.width
          )
        }
        val roundedDrawable = RoundedBitmapDrawableFactory.create(this@loadDrawableCornerRound.resources, bitmat)
        val raduisInt = radius.div(this@loadDrawableCornerRound.layoutParams.width).times(bitmat.width)
        roundedDrawable.cornerRadius = raduisInt
        this@loadDrawableCornerRound.setImageDrawable(roundedDrawable)
      }
    })
}*/
