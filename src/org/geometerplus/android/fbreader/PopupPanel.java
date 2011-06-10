/*
 * Copyright (C) 2010-2011 Geometer Plus <contact@geometerplus.com>
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
 * 02110-1301, USA.
 */

package org.geometerplus.android.fbreader;

import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import org.geometerplus.zlibrary.core.application.ZLApplication;

import org.geometerplus.zlibrary.text.view.ZLTextWordCursor;

import org.geometerplus.fbreader.fbreader.FBReaderApp;

abstract class PopupPanel extends ZLApplication.PopupPanel {
	public ZLTextWordCursor StartPosition;

	protected PopupWindow myWindow;

	PopupPanel(FBReaderApp fbReader) {
		super(fbReader);
	}

	protected final FBReaderApp getReader() {
		return (FBReaderApp)Application;
	}

	@Override
	protected void show_() {
		System.err.println("show_ " + getClass().getName());
		new Exception().printStackTrace();
		if (myWindow != null) {
			myWindow.show();
		}
	}

	@Override
	protected void hide_() {
		System.err.println("hide_ " + getClass().getName());
		if (myWindow != null) {
			myWindow.hide();
		}
	}

	private final void removeWindow() {
		if (myWindow != null) {
			ViewGroup root = (ViewGroup)myWindow.getParent();
			myWindow.hide();
			root.removeView(myWindow);
			myWindow = null;
		}
	}

	public static void removeAllWindows(ZLApplication application) {
		for (ZLApplication.PopupPanel popup : application.popupPanels()) {
			((PopupPanel)popup).removeWindow();
		}
	}

	public static void restoreVisibilities(ZLApplication application) {
		final PopupPanel popup = (PopupPanel)application.getActivePopup();
		if (popup != null) {
			popup.show_();
		}
	}

	public final void initPosition() {
		if (StartPosition == null) {
			StartPosition = new ZLTextWordCursor(getReader().getTextView().getStartCursor());
		}
	}

	public final void storePosition() {
		if (StartPosition != null &&
			!StartPosition.equals(getReader().getTextView().getStartCursor())) {
			getReader().addInvisibleBookmark(StartPosition);
		}
	}

	public abstract void createControlPanel(FBReader activity, RelativeLayout root, PopupWindow.Location location);
}