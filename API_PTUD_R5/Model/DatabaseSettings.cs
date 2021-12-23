﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace API_PTUD_R5.Model
{
    public class DatabaseSettings : IDatabaseSettings
    {
        public string NhaCungCapCollectionName { get; set; }
        public string SanPhamCollectionName { get; set; }
        public string ConnectionString { get; set; }
        public string DatabaseName { get; set; }
    }

    public interface IDatabaseSettings
    {
        string NhaCungCapCollectionName { get; set; }
        string SanPhamCollectionName { get; set; }
        string ConnectionString { get; set; }
        string DatabaseName { get; set; }
    }
}