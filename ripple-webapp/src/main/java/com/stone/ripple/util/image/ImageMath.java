package com.stone.ripple.util.image;

public class ImageMath {

	public final static float PI = (float)Math.PI;

	public final static float HALF_PI = (float)Math.PI/2.0f;

	public final static float QUARTER_PI = (float)Math.PI/4.0f;

	public final static float TWO_PI = (float)Math.PI*2.0f;

	public static float bias(float a, float b) {
		return a/((1.0f/b-2)*(1.0f-a)+1);
	}

	public static float gain(float a, float b) {
		float c = (1.0f/b-2.0f) * (1.0f-2.0f*a);
		if (a < 0.5)
			return a/(c+1.0f);
		else
			return (c-a)/(c-1.0f);
	}

	public static float step(float a, float x) {
		return (x < a) ? 0.0f : 1.0f;
	}

	public static float pulse(float a, float b, float x) {
		return (x < a || x >= b) ? 0.0f : 1.0f;
	}

	public static float smoothPulse(float a1, float a2, float b1, float b2, float x) {
		if (x < a1 || x >= b2)
			return 0;
		if (x >= a2) {
			if (x < b1)
				return 1.0f;
			x = (x - b1) / (b2 - b1);
			return 1.0f - (x*x * (3.0f - 2.0f*x));
		}
		x = (x - a1) / (a2 - a1);
		return x*x * (3.0f - 2.0f*x);
	}

	public static float smoothStep(float a, float b, float x) {
		if (x < a)
			return 0;
		if (x >= b)
			return 1;
		x = (x - a) / (b - a);
		return x*x * (3 - 2*x);
	}

	public static float circleUp(float x) {
		x = 1-x;
		return (float)Math.sqrt(1-x*x);
	}

	public static float circleDown(float x) {
		return 1.0f-(float)Math.sqrt(1-x*x);
	}

	public static float clamp(float x, float a, float b) {
		return (x < a) ? a : (x > b) ? b : x;
	}

	public static int clamp(int x, int a, int b) {
		return (x < a) ? a : (x > b) ? b : x;
	}

	public static double mod(double a, double b) {
		int n = (int)(a/b);
		
		a -= n*b;
		if (a < 0)
			return a + b;
		return a;
	}

	public static float mod(float a, float b) {
		int n = (int)(a/b);
		
		a -= n*b;
		if (a < 0)
			return a + b;
		return a;
	}

	public static int mod(int a, int b) {
		int n = a/b;
		
		a -= n*b;
		if (a < 0)
			return a + b;
		return a;
	}

	public static float triangle(float x) {
		float r = mod(x, 1.0f);
		return 2.0f*(r < 0.5 ? r : 1-r);
	}

	public static float lerp(float t, float a, float b) {
		return a + t * (b - a);
	}
	
	public static int lerp(float t, int a, int b) {
		return (int)(a + t * (b - a));
	}

	public static int mixColors(float t, int rgb1, int rgb2) {
		int a1 = (rgb1 >> 24) & 0xff;
		int r1 = (rgb1 >> 16) & 0xff;
		int g1 = (rgb1 >> 8) & 0xff;
		int b1 = rgb1 & 0xff;
		int a2 = (rgb2 >> 24) & 0xff;
		int r2 = (rgb2 >> 16) & 0xff;
		int g2 = (rgb2 >> 8) & 0xff;
		int b2 = rgb2 & 0xff;
		a1 = lerp(t, a1, a2);
		r1 = lerp(t, r1, r2);
		g1 = lerp(t, g1, g2);
		b1 = lerp(t, b1, b2);
		return (a1 << 24) | (r1 << 16) | (g1 << 8) | b1;
	}

	public static int bilinearInterpolate(float x, float y, int nw, int ne, int sw, int se) {
		float m0, m1;
		int a0 = (nw >> 24) & 0xff;
		int r0 = (nw >> 16) & 0xff;
		int g0 = (nw >> 8) & 0xff;
		int b0 = nw & 0xff;
		int a1 = (ne >> 24) & 0xff;
		int r1 = (ne >> 16) & 0xff;
		int g1 = (ne >> 8) & 0xff;
		int b1 = ne & 0xff;
		int a2 = (sw >> 24) & 0xff;
		int r2 = (sw >> 16) & 0xff;
		int g2 = (sw >> 8) & 0xff;
		int b2 = sw & 0xff;
		int a3 = (se >> 24) & 0xff;
		int r3 = (se >> 16) & 0xff;
		int g3 = (se >> 8) & 0xff;
		int b3 = se & 0xff;

		float cx = 1.0f-x;
		float cy = 1.0f-y;

		m0 = cx * a0 + x * a1;
		m1 = cx * a2 + x * a3;
		int a = (int)(cy * m0 + y * m1);

		m0 = cx * r0 + x * r1;
		m1 = cx * r2 + x * r3;
		int r = (int)(cy * m0 + y * m1);

		m0 = cx * g0 + x * g1;
		m1 = cx * g2 + x * g3;
		int g = (int)(cy * m0 + y * m1);

		m0 = cx * b0 + x * b1;
		m1 = cx * b2 + x * b3;
		int b = (int)(cy * m0 + y * m1);

		return (a << 24) | (r << 16) | (g << 8) | b;
	}

	public static int brightnessNTSC(int rgb) {
		int r = (rgb >> 16) & 0xff;
		int g = (rgb >> 8) & 0xff;
		int b = rgb & 0xff;
		return (int)(r*0.299f + g*0.587f + b*0.114f);
	}
	
	private final static float m00 = -0.5f;
	private final static float m01 =  1.5f;
	private final static float m02 = -1.5f;
	private final static float m03 =  0.5f;
	private final static float m10 =  1.0f;
	private final static float m11 = -2.5f;
	private final static float m12 =  2.0f;
	private final static float m13 = -0.5f;
	private final static float m20 = -0.5f;
	private final static float m21 =  0.0f;
	private final static float m22 =  0.5f;
	private final static float m23 =  0.0f;
	private final static float m30 =  0.0f;
	private final static float m31 =  1.0f;
	private final static float m32 =  0.0f;
	private final static float m33 =  0.0f;

	public static float spline(float x, int numKnots, float[] knots) {
		int span;
		int numSpans = numKnots - 3;
		float k0, k1, k2, k3;
		float c0, c1, c2, c3;
		
		if (numSpans < 1)
			throw new IllegalArgumentException("Too few knots in spline");
		
		x = clamp(x, 0, 1) * numSpans;
		span = (int)x;
		if (span > numKnots-4)
			span = numKnots-4;
		x -= span;

		k0 = knots[span];
		k1 = knots[span+1];
		k2 = knots[span+2];
		k3 = knots[span+3];
		
		c3 = m00*k0 + m01*k1 + m02*k2 + m03*k3;
		c2 = m10*k0 + m11*k1 + m12*k2 + m13*k3;
		c1 = m20*k0 + m21*k1 + m22*k2 + m23*k3;
		c0 = m30*k0 + m31*k1 + m32*k2 + m33*k3;
		
		return ((c3*x + c2)*x + c1)*x + c0;
	}
	
	public static float spline(float x, int numKnots, int[] xknots, int[] yknots) {
		int span;
		int numSpans = numKnots - 3;
		float k0, k1, k2, k3;
		float c0, c1, c2, c3;
		
		if (numSpans < 1)
			throw new IllegalArgumentException("Too few knots in spline");
		
		for (span = 0; span < numSpans; span++)
			if (xknots[span+1] > x)
				break;
		if (span > numKnots-3)
			span = numKnots-3;
		float t = (float)(x-xknots[span]) / (xknots[span+1]-xknots[span]);
		span--;
		if (span < 0) {
			span = 0;
			t = 0;
		}

		k0 = yknots[span];
		k1 = yknots[span+1];
		k2 = yknots[span+2];
		k3 = yknots[span+3];
		
		c3 = m00*k0 + m01*k1 + m02*k2 + m03*k3;
		c2 = m10*k0 + m11*k1 + m12*k2 + m13*k3;
		c1 = m20*k0 + m21*k1 + m22*k2 + m23*k3;
		c0 = m30*k0 + m31*k1 + m32*k2 + m33*k3;
		
		return ((c3*t + c2)*t + c1)*t + c0;
	}

	public static int colorSpline(float x, int numKnots, int[] knots) {
		int span;
		int numSpans = numKnots - 3;
		float k0, k1, k2, k3;
		float c0, c1, c2, c3;
		
		if (numSpans < 1)
			throw new IllegalArgumentException("Too few knots in spline");
		
		x = clamp(x, 0, 1) * numSpans;
		span = (int)x;
		if (span > numKnots-4)
			span = numKnots-4;
		x -= span;

		int v = 0;
		for (int i = 0; i < 4; i++) {
			int shift = i * 8;
			
			k0 = (knots[span] >> shift) & 0xff;
			k1 = (knots[span+1] >> shift) & 0xff;
			k2 = (knots[span+2] >> shift) & 0xff;
			k3 = (knots[span+3] >> shift) & 0xff;
			
			c3 = m00*k0 + m01*k1 + m02*k2 + m03*k3;
			c2 = m10*k0 + m11*k1 + m12*k2 + m13*k3;
			c1 = m20*k0 + m21*k1 + m22*k2 + m23*k3;
			c0 = m30*k0 + m31*k1 + m32*k2 + m33*k3;
			int n = (int)(((c3*x + c2)*x + c1)*x + c0);
			if (n < 0)
				n = 0;
			else if (n > 255)
				n = 255;
			v |= n << shift;
		}
		
		return v;
	}

	/**
	 * Compute a Catmull-Rom spline for RGB values, but with variable knot spacing.
	 * @param x the input parameter
	 * @param numKnots the number of knots in the spline
	 * @param xknots the array of knot x values
	 * @param yknots the array of knot y values
	 * @return the spline value
	 */
	public static int colorSpline(int x, int numKnots, int[] xknots, int[] yknots) {
		int span;
		int numSpans = numKnots - 3;
		float k0, k1, k2, k3;
		float c0, c1, c2, c3;
		
		if (numSpans < 1)
			throw new IllegalArgumentException("Too few knots in spline");
		
		for (span = 0; span < numSpans; span++)
			if (xknots[span+1] > x)
				break;
		if (span > numKnots-3)
			span = numKnots-3;
		float t = (float)(x-xknots[span]) / (xknots[span+1]-xknots[span]);
		span--;
		if (span < 0) {
			span = 0;
			t = 0;
		}

		int v = 0;
		for (int i = 0; i < 4; i++) {
			int shift = i * 8;
			
			k0 = (yknots[span] >> shift) & 0xff;
			k1 = (yknots[span+1] >> shift) & 0xff;
			k2 = (yknots[span+2] >> shift) & 0xff;
			k3 = (yknots[span+3] >> shift) & 0xff;
			
			c3 = m00*k0 + m01*k1 + m02*k2 + m03*k3;
			c2 = m10*k0 + m11*k1 + m12*k2 + m13*k3;
			c1 = m20*k0 + m21*k1 + m22*k2 + m23*k3;
			c0 = m30*k0 + m31*k1 + m32*k2 + m33*k3;
			int n = (int)(((c3*t + c2)*t + c1)*t + c0);
			if (n < 0)
				n = 0;
			else if (n > 255)
				n = 255;
			v |= n << shift;
		}
		
		return v;
	}

	public static void resample(int[] source, int[] dest, int length, int offset, int stride, float[] out) {
		int i, j;
		float sizfac;
		float inSegment;
		float outSegment;
		int a, r, g, b, nextA, nextR, nextG, nextB;
		float aSum, rSum, gSum, bSum;
		float[] in;
		int srcIndex = offset;
		int destIndex = offset;
		int lastIndex = source.length;
		int rgb;

		in = new float[length+2];
		i = 0;
		for (j = 0; j < length; j++) {
			while (out[i+1] < j)
				i++;
			in[j] = i + (float) (j - out[i]) / (out[i + 1] - out[i]);
		}
		in[length] = length;
		in[length+1] = length;

		inSegment  = 1.0f;
		outSegment = in[1];
		sizfac = outSegment;
		aSum = rSum = gSum = bSum = 0.0f;
		rgb = source[srcIndex];
		a = (rgb >> 24) & 0xff;
		r = (rgb >> 16) & 0xff;
		g = (rgb >> 8) & 0xff;
		b = rgb & 0xff;
		srcIndex += stride;
		rgb = source[srcIndex];
		nextA = (rgb >> 24) & 0xff;
		nextR = (rgb >> 16) & 0xff;
		nextG = (rgb >> 8) & 0xff;
		nextB = rgb & 0xff;
		srcIndex += stride;
		i = 1;

		while (i <= length) {
			float aIntensity = inSegment * a + (1.0f - inSegment) * nextA;
			float rIntensity = inSegment * r + (1.0f - inSegment) * nextR;
			float gIntensity = inSegment * g + (1.0f - inSegment) * nextG;
			float bIntensity = inSegment * b + (1.0f - inSegment) * nextB;
			if (inSegment < outSegment) {
				aSum += (aIntensity * inSegment);
				rSum += (rIntensity * inSegment);
				gSum += (gIntensity * inSegment);
				bSum += (bIntensity * inSegment);
				outSegment -= inSegment;
				inSegment = 1.0f;
				a = nextA;
				r = nextR;
				g = nextG;
				b = nextB;
				if (srcIndex < lastIndex)
					rgb = source[srcIndex];
				nextA = (rgb >> 24) & 0xff;
				nextR = (rgb >> 16) & 0xff;
				nextG = (rgb >> 8) & 0xff;
				nextB = rgb & 0xff;
				srcIndex += stride;
			} else {
				aSum += (aIntensity * outSegment);
				rSum += (rIntensity * outSegment);
				gSum += (gIntensity * outSegment);
				bSum += (bIntensity * outSegment);
				dest[destIndex] = 
					((int)Math.min(aSum/sizfac, 255) << 24) |
					((int)Math.min(rSum/sizfac, 255) << 16) |
					((int)Math.min(gSum/sizfac, 255) << 8) |
					(int)Math.min(bSum/sizfac, 255);
				destIndex += stride;
				aSum = rSum = gSum = bSum = 0.0f;
				inSegment -= outSegment;
				outSegment = in[i+1] - in[i];
				sizfac = outSegment;
				i++;
			}
		}
	}

	public static void premultiply( int[] p, int offset, int length ) {
        length += offset;
		for ( int i = offset; i < length; i ++ ) {
            int rgb = p[i];
            int a = (rgb >> 24) & 0xff;
            int r = (rgb >> 16) & 0xff;
            int g = (rgb >> 8) & 0xff;
            int b = rgb & 0xff;
            float f = a * (1.0f / 255.0f);
            r *= f;
            g *= f;
            b *= f;
            p[i] = (a << 24) | (r << 16) | (g << 8) | b;
        }
    }

	public static void unpremultiply( int[] p, int offset, int length ) {
        length += offset;
		for ( int i = offset; i < length; i ++ ) {
            int rgb = p[i];
            int a = (rgb >> 24) & 0xff;
            int r = (rgb >> 16) & 0xff;
            int g = (rgb >> 8) & 0xff;
            int b = rgb & 0xff;
            if ( a != 0 && a != 255 ) {
                float f = 255.0f / a;
                r *= f;
                g *= f;
                b *= f;
                if ( r > 255 )
                    r = 255;
                if ( g > 255 )
                    g = 255;
                if ( b > 255 )
                    b = 255;
                p[i] = (a << 24) | (r << 16) | (g << 8) | b;
            }
        }
    }
}
