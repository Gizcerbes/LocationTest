package com.uogames.locationtest

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.uogames.locationtest.data.file.FileProvider
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import java.lang.ref.WeakReference
import java.util.UUID

private val fileUUID = UUID.randomUUID()

@RunWith(AndroidJUnit4::class)
class TestFileRepo {

	private val file = ByteArray(5) { it.toByte() }


	@Test
	fun saveFileTest() = runBlocking {
		val appContext = InstrumentationRegistry.getInstrumentation().targetContext

		val fr = FileProvider(WeakReference(appContext))
		fr.save(file, fileUUID)
		Assert.assertTrue(true)

	}

	@Test
	fun saveAndReadFileTest() = runBlocking{
		val appContext = InstrumentationRegistry.getInstrumentation().targetContext

		val fr = FileProvider(WeakReference(appContext),)
		fr.save(file, fileUUID)

		val r = fr.get(fileUUID)

		val d1 = StringBuilder().apply { file.forEach { this.append(it) } }
		val d2 = StringBuilder().apply { r?.forEach { this.append(it) } }

		Assert.assertEquals(d1.toString(), d2.toString())

	}

	@Test
	fun saveAndExistCheck() = runBlocking {
		saveFileTest()
		val appContext = InstrumentationRegistry.getInstrumentation().targetContext
		val fr = FileProvider(WeakReference(appContext))
		val exists = fr.exists(fileUUID)

		Assert.assertTrue(exists)
	}

	@Test
	fun deleteFileTest() = runBlocking {
		saveFileTest()
		val appContext = InstrumentationRegistry.getInstrumentation().targetContext
		val fr = FileProvider(WeakReference(appContext))
		Assert.assertTrue( fr.exists(fileUUID))
		Assert.assertTrue(fr.delete(fileUUID))
		Assert.assertFalse(fr.exists(fileUUID))
		Assert.assertFalse(fr.delete(fileUUID))
	}

	@Test
	fun countAndClearFileCheck() = runBlocking {
		val appContext = InstrumentationRegistry.getInstrumentation().targetContext
		val fr = FileProvider(WeakReference(appContext))
		fr.save(file, fileUUID)
		repeat(5){
			fr.save(file, UUID.randomUUID())
		}
		Assert.assertEquals(6, fr.count())
		fr.clear()
		Assert.assertEquals(0, fr.count())
	}


}