package Functional;

public class Convolution
{
	public double[] kernel;


	public Convolution(double[] kernel)
	{
		this.kernel = kernel;
	}

	public double[] linearConv(double[] sample)
	{
		double[] convoluted = new double[sample.length + kernel.length - 1];

		for (int i = 0; i < sample.length + kernel.length - 1; i++)
		{
			int kmin, kmax, k;

			convoluted[i] = 0;

			kmin = (i >= kernel.length - 1)
					? i - (kernel.length - 1)
					: 0;
			kmax = (i < sample.length - 1)
					? i
					: sample.length - 1;

			for (k = kmin; k <= kmax; k++)
			{
				convoluted[i] += sample[k] * kernel[i - k];
			}

		}
		return convoluted;
	}

	public double[] linearConv(double[] sample, double[] kernel)
	{
		setKernel(kernel);
		return linearConv(sample);
	}

	
	public void setKernel(double[] kernel)
	{
		this.kernel = kernel;
	}

	
	public double[] getKernel()
	{
		return kernel;
	}
}
