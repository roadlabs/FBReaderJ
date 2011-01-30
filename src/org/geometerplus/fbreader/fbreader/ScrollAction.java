/*
 * Copyright (C) 2007-2011 Geometer Plus <contact@geometerplus.com>
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

package org.geometerplus.fbreader.fbreader;

import org.geometerplus.zlibrary.core.application.ZLApplication;
import org.geometerplus.zlibrary.text.view.ZLTextView;

class ScrollAction extends FBAction {
	private final boolean myForward;

	ScrollAction(FBReaderApp fbreader, boolean forward) {
		super(fbreader);
		myForward = forward;
	}

	public boolean isEnabled() {
		return true;
	}

	public void run() {
		Reader.getTextView().scrollPage(myForward, ZLTextView.ScrollingMode.SCROLL_LINES, 1);
		ZLApplication.Instance().repaintView();
	}
}