using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Exam_test
{
    class Program
    {
        static void Main(string[] args)
        {
            var n = int.Parse(Console.ReadLine());
            var m = int.Parse(Console.ReadLine());
            var y = int.Parse(Console.ReadLine());
            var c = n;
            var count = 0;
            while (n >= m)
            {
                n = n - m;
                count++;
                if (c/2 == n)
                {
                    n = n / y;
                }
            }
            Console.WriteLine(n);
            Console.WriteLine(count);
        }
    }
}
